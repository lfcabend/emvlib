package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationIdentifier(val aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationIdentifier.tag

  override val value: Seq[Byte] = aid.value

}

object ApplicationIdentifier extends EMVAIDSpec[ApplicationIdentifier] {

  val tag: BerTag = "9F06"

}
