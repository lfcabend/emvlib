package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDefinitionFileName(aid: AID)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = DataAuthenticationCode.tag

  override val value = aid.value

  override val templates = Set(ApplicationTemplate.tag)

}

object DirectoryDefinitionFileName extends EMVAIDSpec[DirectoryDefinitionFileName] {

  val tag: BerTag = berTag"9D"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(DirectoryDefinitionFileName, parseB(_).map(AID(_)))

}