package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class DynamicDataAuthenticationDataObjectList  (val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = DynamicDataAuthenticationDataObjectList.tag

}

object DynamicDataAuthenticationDataObjectList extends EMVDOLSpec[DynamicDataAuthenticationDataObjectList] {

  val tag: BerTag = "9F49"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}