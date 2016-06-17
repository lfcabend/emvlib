package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/17/16.
  */
case class CardRiskManagementDataObjectList2(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = CardRiskManagementDataObjectList2.tag

}

object CardRiskManagementDataObjectList2 extends EMVDOLSpec[CardRiskManagementDataObjectList2] {

  val tag: BerTag = "8D"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}
