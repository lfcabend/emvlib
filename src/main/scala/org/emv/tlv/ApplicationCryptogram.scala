package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVBinaryWithLengthSpec, TemplateTag, EMVTLVLeaf}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCryptogram (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationCryptogram.tag

}

object ApplicationCryptogram extends EMVDefaultBinaryWithLengthSpec[ApplicationCryptogram] {

  val tag: BerTag = "9F36"

  val length: Int = 8

}