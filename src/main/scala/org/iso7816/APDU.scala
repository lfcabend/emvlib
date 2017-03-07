package org.iso7816

import scodec.bits.ByteVector

object APDU {

  type Class = Byte

  type Instruction = Byte

  type Param = Byte

  type P1 = Param

  type P2 = Param

  trait ParamContainer {

    val value: Param

  }

  case class Parameter(val value: Byte) extends ParamContainer


  abstract class APDUCommand(cla: Class,
                             ins: Instruction,
                             p1: P1,
                             p2: P2,
                             data: Option[ByteVector], le: Option[Byte]) {

      def serialize = {
        val header = ByteVector(cla :: ins :: p1 :: p2 :: Nil)
        val headerPlusData: ByteVector = data match {
          case Some(x) => header ++ ByteVector(x.size.toByte) ++ x
          case None    => header
        }
        le match {
          case Some(x) => headerPlusData ++ ByteVector(x)
          case None => headerPlusData
        }
      }

  }

  abstract class APDUCommandResponse(val responseBody: Option[ByteVector], val statusWord: StatusWordT) {

    def serialize: ByteVector = {
      responseBody match {
        case Some(x) => x ++ statusWord.serialize
        case None => statusWord.serialize
      }
    }

  }


  val ACTIVE_FILE: Instruction = 0x44.toByte
  val APPEND_RECORD: Instruction = 0xE2.toByte
  val CHANGE_REFERENCE_DATA: Instruction = 0x24.toByte
  val CREATE_FILE: Instruction = 0xE0.toByte
  val DEACTIVATE_FILE: Instruction = 0x04.toByte
  val DELETE_FILE: Instruction = 0xE4.toByte
  val DISABLE_VERIFICATION_REQUIREMENT: Instruction = 0x26.toByte
  val ENABLE_VERIFICATION_REQUIREMENT: Instruction = 0x28.toByte
  val ENVELOPE_C2: Instruction = 0xC2.toByte
  val ENVELOPE_C3: Instruction = 0xC3.toByte
  val ERASE_BINARY_0E: Instruction = 0x0E.toByte
  val ERASE_BINARY_0F: Instruction = 0x0F.toByte
  val ERASE_RECORD: Instruction  = 0x0C.toByte
  val EXTERNAL_AUTHENTICATE: Instruction = 0x82.toByte
  val MUTUAL_AUTHENTICATE: Instruction = 0x82.toByte
  val GENERAL_AUTHENTICATE_86: Instruction = 0x86.toByte
  val GENERAL_AUTHENTICATE_87: Instruction = 0x87.toByte
  val GENERATE_ASYMMETRIC_KEY_PAIR: Instruction = 0x46.toByte
  val GET_CHALLENGE: Instruction = 0x84.toByte
  val GET_DATA_CA : Instruction = 0xCA.toByte
  val GET_DATA_CB : Instruction = 0xCB.toByte
  val GET_RESPONSE: Instruction = 0xC0.toByte
  val INTERNAL_AUTHENTICATE: Instruction = 0x88.toByte
  val MANAGE_CHANNEL: Instruction = 0x70.toByte
  val MANAGE_SECURITY_ENVIRONMENT: Instruction = 0x22.toByte
  val PERFORM_SCQL_OPERATION: Instruction = 0x10.toByte
  val PERFORM_SECURITY_OPERATION: Instruction = 0x2A.toByte
  val PERFORM_TRANSACTION_OPERATION: Instruction = 0x12.toByte
  val PERFORM_USER_OPERATION: Instruction = 0x14.toByte
  val PUT_DATA_DA: Instruction = 0xDA.toByte
  val PUT_DATA_DB: Instruction = 0xDB.toByte
  val READ_BINARY_B0: Instruction = 0xB0.toByte
  val READ_BINARY_B1: Instruction = 0xB1.toByte
  val READ_RECORD_B2: Instruction = 0xB2.toByte
  val READ_RECORD_B3: Instruction = 0xB2.toByte
  val RESET_RETRY_COUNTER : Instruction = 0x2C.toByte
  val SEARCH_BINARY_A0: Instruction = 0xA0.toByte
  val SEARCH_BINARY_A1: Instruction = 0xA1.toByte
  val SEARCH_RECORD: Instruction = 0xA2.toByte
  val SELECT: Instruction = 0xA4.toByte
  val TERMINATE_CARD_USAGE: Instruction = 0xFE.toByte
  val TERMINATE_DF: Instruction = 0xE6.toByte
  val TERMINATE_EF: Instruction = 0xE8.toByte
  val UPDATE_BINARY_D6: Instruction = 0xD6.toByte
  val UPDATE_BINARY_D7: Instruction = 0xD7.toByte
  val UPDATE_RECORD_DC: Instruction = 0xDC.toByte
  val UPDATE_RECORD_DD: Instruction = 0xDD.toByte
  val VERIFY_20: Instruction  = 0x20.toByte
  val VERIFY_21: Instruction  = 0x21.toByte
  val WRITE_BINARY_D0: Instruction = 0xD0.toByte
  val WRITE_BINARY_D1: Instruction = 0xD1.toByte
  val WRITE_RECORD: Instruction = 0xD2.toByte

}
