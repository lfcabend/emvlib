package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDefinitionFileName(aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = DataAuthenticationCode.tag

  override val value: Seq[Byte] = aid.value

}

object DirectoryDefinitionFileName extends EMVAIDSpec[DirectoryDefinitionFileName] {

  val tag: BerTag = "9D"

}