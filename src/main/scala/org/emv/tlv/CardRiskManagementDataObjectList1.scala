package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/16/16.
  */
case class CardRiskManagementDataObjectList1(val list: List[(BerTag, Int)])
  extends DOL with TemplateTag {

  override val tag = CardRiskManagementDataObjectList1.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object CardRiskManagementDataObjectList1 extends EMVDOLSpec[CardRiskManagementDataObjectList1] {

  val tag = berTag"8C"

  override val maxLength = 252

  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CardRiskManagementDataObjectList1, parseDOL(_))

}
