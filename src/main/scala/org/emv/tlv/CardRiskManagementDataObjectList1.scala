package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDOLSpec, EMVBinaryWithVarLengthSpec, EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/16/16.
  */
case class CardRiskManagementDataObjectList1(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = CardRiskManagementDataObjectList1.tag

}

object CardRiskManagementDataObjectList1 extends EMVDOLSpec[CardRiskManagementDataObjectList1] {

  val tag: BerTag = "8C"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}
