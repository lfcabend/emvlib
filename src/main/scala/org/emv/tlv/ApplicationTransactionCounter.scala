package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/7/16.
  */
case class ApplicationTransactionCounter(override val value: Seq[Byte])
  extends EMVTLVLeaf with BinaryNumber {

  override val tag: BerTag = ApplicationTransactionCounter.tag

}

object ApplicationTransactionCounter extends EMVDefaultBinaryWithLengthSpec[ApplicationTransactionCounter] {

  val tag: BerTag = "9F36"

  val length: Int = 2

}