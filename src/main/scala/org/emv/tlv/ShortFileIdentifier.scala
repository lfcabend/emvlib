package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class ShortFileIdentifier(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = ShortFileIdentifier.tag

}

object ShortFileIdentifier extends EMVDefaultBinaryWithLengthSpec[ShortFileIdentifier] {

  val tag: BerTag = "88"

  override val length: Int = 1

}