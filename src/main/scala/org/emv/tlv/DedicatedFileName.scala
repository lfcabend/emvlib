package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVBinaryWithVarLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.tlv.TLV.BerTag

/**
  * Created by lau on 9/1/16.
  */
case class DedicatedFileName (aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = DataAuthenticationCode.tag

  override val value: Seq[Byte] = aid.value

}

object DedicatedFileName extends EMVAIDSpec[DedicatedFileName] {

  val tag: BerTag = "4F"

}