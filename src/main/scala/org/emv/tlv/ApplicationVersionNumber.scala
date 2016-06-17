package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/9/16.
  */
case class ApplicationVersionNumber(override val value:Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationVersionNumber.tag

}

object ApplicationVersionNumber extends EMVDefaultBinaryWithLengthSpec[ApplicationVersionNumber] {

  val tag: BerTag = "9F08"

  val length: Int = 2

}