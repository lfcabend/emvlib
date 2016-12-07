package org.emv

import nfc._

import scalaz.concurrent.Task

/**
  * Created by lau on 12/5/16.
  */
object LibNFCCard extends CardTrait {

  System.load("/home/pi/swiglibnfc/nfcjni.so");


  override def waitForCardOnTerminal: Task[Option[ConnectionContext]] = Task {
    val ctxPtr = nfc.new_SWIGTYPE_p_p_nfc_context()
    nfc.nfc_init(ctxPtr)
    val ctx = nfc.SWIGTYPE_p_p_nfc_context_value(ctxPtr)
    nfc.delete_SWIGTYPE_p_p_nfc_context(ctxPtr)
    val reader = nfc.nfc_open(ctx, null)
    val mod = new nfc_modulation()
    mod.setNbr(nfc_baud_rate.NBR_106)
    mod.setNmt(nfc_modulation_type.NMT_ISO14443A)
    val target = new nfc_target()

    //    val nfc_dep_mode1 = nfc_dep_mode.NDM_PASSIVE
    //    val nfc_baud_rate1 = nfc_baud_rate.NBR_106
    //
    //    val nfc_dep_info1 = nfc_dep_info
    //    val result = nfc.nfc_initiator_select_dep_target (reader, nfc_dep_mode1, nfc_baud_rate1, null, target, 1000)

    //      nfc_initiator_poll_target (nfc_device *pnd, const nfc_modulation *pnmTargetTypes, const size_t szTargetTypes, const uint8_t uiPollNr, const uint8_t uiPeriod, nfc_target *pnt)
    //    nfc_initiator_select_passive_target (nfc_device *pnd, const nfc_modulation nm, const uint8_t *pbtInitData, const size_t szInitData, nfc_target *pnt)
    val result = nfc.nfc_initiator_select_passive_target(reader, mod, Array(), 0L, target);
    result match {
      case x if x > 0 => {
        val nai = target.getNti().getNai()
        Some(LibNFCConnectionContext(ctxPtr, ctx, reader, mod, target))
      }
      case 0 => None
      case x if x < 0 => None
    }
  }

  override def close(context: Option[ConnectionContext]): Task[Unit] =
    context match {
      case Some(LibNFCConnectionContext(ctxPtr, ctx, reader, mod, target)) => Task {
        nfc.nfc_close(reader)
        nfc.nfc_exit(ctx)
      }
      case _ => Task(Unit)
    }

  override def transmit(context: Option[ConnectionContext], commandBytes: Seq[Byte]): Task[Seq[Byte]] =
    context match {
      case Some(LibNFCConnectionContext(ctxPtr, ctx, reader, mod, target)) => Task {
        val responseArr = Array.fill[Byte](264) {
          0
        }
        val result = nfc.nfc_initiator_transceive_bytes(reader, commandBytes.asInstanceOf[Array[Byte]],
          commandBytes.length.asInstanceOf[Long], responseArr, responseArr.length, 1000)
        if (result < 0) {
          Nil
        } else {
          val resultArr = Array.fill[Byte](result) {
            0
          }
          Array.copy(responseArr, 0, resultArr, 0, result)
          resultArr.toList
        }
      }
      case _ => Task(Nil)
    }

}
