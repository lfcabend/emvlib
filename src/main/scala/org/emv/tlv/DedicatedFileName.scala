package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 9/1/16.
  */
case class DedicatedFileName (aid: AID) extends EMVTLVLeaf with TemplateTag {

  override val tag = DedicatedFileName.tag

  override val value = aid.value

  override val templates = Set(FileControlInformationTemplate.tag)

}

object DedicatedFileName extends EMVAIDSpec[DedicatedFileName] {

  val tag: BerTag = berTag"84"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(DedicatedFileName, parseB(_).map(AID(_)))


}