package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/17/16.
  */
case class CardRiskManagementDataObjectList2(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = CardRiskManagementDataObjectList2.tag

}

object CardRiskManagementDataObjectList2 extends EMVDOLSpec[CardRiskManagementDataObjectList2] {

  val tag: BerTag = berTag"8D"

  override val maxLength: Int = 252

  override val minLength: Int = 0

  def parser: Parser[CardRiskManagementDataObjectList2] =
    parseEMVBySpec(CardRiskManagementDataObjectList2, parseDOL(_))
}
