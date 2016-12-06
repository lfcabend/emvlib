package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/17/16.
  */
case class LastOnlineApplicationTransactionCounterRegister(override val value: Seq[Byte]) extends EMVTLVLeaf with BinaryNumber {

  override val tag: BerTag = LastOnlineApplicationTransactionCounterRegister.tag

}

object LastOnlineApplicationTransactionCounterRegister extends EMVDefaultBinaryWithLengthSpec[LastOnlineApplicationTransactionCounterRegister] {

  val tag: BerTag = "9F13"

  val length: Int = 2

}
