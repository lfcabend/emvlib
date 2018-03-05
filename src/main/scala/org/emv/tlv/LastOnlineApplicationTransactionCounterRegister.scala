package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/17/16.
  */
case class LastOnlineApplicationTransactionCounterRegister(override val value: ByteVector)
  extends EMVTLVLeaf with BinaryNumber {

  override val tag = LastOnlineApplicationTransactionCounterRegister.tag

}

object LastOnlineApplicationTransactionCounterRegister extends EMVDefaultBinaryWithLengthSpec[LastOnlineApplicationTransactionCounterRegister] {

  val tag = berTag"9F13"

  val length = 2

  def parser = parseEMVBySpec(LastOnlineApplicationTransactionCounterRegister, parseB(_))


}
