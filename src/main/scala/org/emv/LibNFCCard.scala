package org.emv

import com.typesafe.scalalogging.LazyLogging
import nfc._
import com.softwaremill.quicklens._
import scodec.bits.ByteVector

import scalaz.concurrent.Task

/**
  * Created by lau on 12/5/16.
  */
object LibNFCCard extends CardTrait with LazyLogging {

  logger.debug("Loading /home/pi/swiglibnfc/nfcjni.so")
  System.load("/home/pi/swiglibnfc/nfcjni.so");

  override def initialize(connectionConfig: ConnectionConfig): Task[ConnectionContext] = Task {

    val ctxPtr = nfc.new_SWIGTYPE_p_p_nfc_context()
    nfc.nfc_init(ctxPtr)
    if (ctxPtr == null) {
      val msg: String = "Unable to init libnfc (malloc)"
      logger.error(msg)
      throw new ReaderInitializationError(msg)
    }
    logger.info(s"nfc version: ${nfc.nfc_version}")
    val ctx = nfc.SWIGTYPE_p_p_nfc_context_value(ctxPtr)
    nfc.delete_SWIGTYPE_p_p_nfc_context(ctxPtr)
    val reader = nfc.nfc_open(ctx, null)
    if (reader == null) {
      val msg: String = "Unable to open NFC device."
      logger.error(msg)
      throw new ReaderInitializationError(msg)
    }
    logger.info("opened NFC device.")

    if (nfc.nfc_initiator_init(reader) < 0) {
      nfc.nfc_perror(reader, "nfc_initiator_init")
      val msg: String = "Unable to open NFC device."
      logger.error(msg)
      throw new ReaderInitializationError(msg)
    }

    val mod = new nfc_modulation()
    mod.setNbr(nfc_baud_rate.NBR_106)
    mod.setNmt(nfc_modulation_type.NMT_ISO14443A)

    logger.debug(s"NFC reader: ${nfc.nfc_device_get_name(reader)} opened")
    LibNFCConnectionContext(connectionConfig, ctxPtr, ctx, reader, mod, None)
  }

  override def waitForCardOnTerminal(context: ConnectionContext): Task[ConnectionContext] = context match {
    case c@LibNFCConnectionContext(connectionConfig, ctxPtr, ctx, reader, mod, target) => Task {

      val target = new nfc_target()

      //    val nfc_dep_mode1 = nfc_dep_mode.NDM_PASSIVE
      //    val nfc_baud_rate1 = nfc_baud_rate.NBR_106
      //
      //    val nfc_dep_info1 = nfc_dep_info
      //    val result = nfc.nfc_initiator_select_dep_target (reader, nfc_dep_mode1, nfc_baud_rate1, null, target, 1000)

      //      nfc_initiator_poll_target (nfc_device *pnd, const nfc_modulation *pnmTargetTypes, const size_t szTargetTypes, const uint8_t uiPollNr, const uint8_t uiPeriod, nfc_target *pnt)
      //    nfc_initiator_select_passive_target (nfc_device *pnd, const nfc_modulation nm, const uint8_t *pbtInitData, const size_t szInitData, nfc_target *pnt)
      val result = nfc.nfc_initiator_select_passive_target(reader, mod, Array(), 0L, target);
      logger.debug(s"result of selecting a target: ${result}")
      result match {
        case x if x > 0 => {
          val nai = target.getNti().getNai()
          logger.debug(s"target UID: ${ByteVector(nai.getAbtUid).toHex}")
          logger.debug(s"target ATS: ${ByteVector(nai.getAbtAts).toHex}")
          modify(c)(_.target).setTo(Some(target))
        }
        case 0 => throw new Exception()
        case x if x < 0 => throw new Exception()
      }
    }
    case _ => InvalidContextType(context)
  }

  override def close(context: ConnectionContext): Task[Unit] =
    context match {
      case LibNFCConnectionContext(_, ctxPtr, ctx, reader, mod, target) => Task {
        nfc.nfc_close(reader)
        nfc.nfc_exit(ctx)
      }
      case _ => InvalidContextType(context)
    }

  override def transmit(context: ConnectionContext, commandBytes: ByteVector): Task[ByteVector] =
    context match {
      case LibNFCConnectionContext(_, ctxPtr, ctx, reader, mod, Some(target)) => Task {
        val responseArr = Array.fill[Byte](264) {
          0
        }
        logger.debug(s"card -> ${commandBytes.toHex}")
        val result = nfc.nfc_initiator_transceive_bytes(reader, commandBytes.toArray,
          commandBytes.length.toLong, responseArr, responseArr.length, 1000)
        if (result < 0) {
          logger.debug(s"error result: ${result}")
          ByteVector.empty
        } else {
          val resultArr = Array.fill[Byte](result) {
            0
          }
          Array.copy(responseArr, 0, resultArr, 0, result)
          logger.debug(s"card <- ${ByteVector(resultArr).toHex}")
          ByteVector(resultArr)
        }
      }
      case _ => InvalidContextType(context)
    }

}
