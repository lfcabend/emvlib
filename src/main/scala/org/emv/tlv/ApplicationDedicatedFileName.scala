package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationDedicatedFileName(aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationDedicatedFileName.tag

  override val value: Seq[Byte] = aid.value

}

object ApplicationDedicatedFileName extends EMVAIDSpec[ApplicationDedicatedFileName] {

  val tag: BerTag = "4F"

}
