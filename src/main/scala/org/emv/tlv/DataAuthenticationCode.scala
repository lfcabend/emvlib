package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 9/1/16.
  */
case class DataAuthenticationCode (override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = DataAuthenticationCode.tag

}

object DataAuthenticationCode extends EMVDefaultBinaryWithLengthSpec[DataAuthenticationCode] {

  override val length: Int = 2

  override val tag: BerTag = "9F45"

}