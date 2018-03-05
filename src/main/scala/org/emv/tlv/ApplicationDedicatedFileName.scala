package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 6/5/16.
  */
case class ApplicationDedicatedFileName(aid: AID)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = ApplicationDedicatedFileName.tag

  override val value = aid.value

  override val templates = Set(ApplicationTemplate.tag)
}

object ApplicationDedicatedFileName extends EMVAIDSpec[ApplicationDedicatedFileName] {

  val tag = berTag"4F"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(ApplicationDedicatedFileName,
    parseB(_).map(AID(_)))

}
