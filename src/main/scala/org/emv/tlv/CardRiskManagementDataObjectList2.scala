package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/17/16.
  */
case class CardRiskManagementDataObjectList2(val list: List[(BerTag, Int)])
  extends DOL with TemplateTag {

  override val tag = CardRiskManagementDataObjectList2.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object CardRiskManagementDataObjectList2 extends EMVDOLSpec[CardRiskManagementDataObjectList2] {

  val tag = berTag"8D"

  override val maxLength = 252

  override val minLength = 0

  def parser = parseEMVBySpec(CardRiskManagementDataObjectList2, parseDOL(_))
}
