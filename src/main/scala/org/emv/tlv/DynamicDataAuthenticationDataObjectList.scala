package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DynamicDataAuthenticationDataObjectList  (val list: List[(BerTag, Int)])
  extends DOL with TemplateTag {

  override val tag  = DynamicDataAuthenticationDataObjectList.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object DynamicDataAuthenticationDataObjectList extends EMVDOLSpec[DynamicDataAuthenticationDataObjectList] {

  val tag = berTag"9F49"

  override val maxLength = 252

  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(DynamicDataAuthenticationDataObjectList, parseDOL(_))

}