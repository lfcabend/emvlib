package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptIdentifier  (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerScriptIdentifier.tag

}

object IssuerScriptIdentifier extends EMVDefaultBinaryWithLengthSpec[IssuerScriptIdentifier] {

  val tag: BerTag = "9F18"

  override val length: Int = 4
}

