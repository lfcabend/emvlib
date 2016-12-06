package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/3/16.
  */
case class DefaultDynamicDataAuthenticationDataObjectList(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = DefaultDynamicDataAuthenticationDataObjectList.tag

}

object DefaultDynamicDataAuthenticationDataObjectList extends EMVDOLSpec[DefaultDynamicDataAuthenticationDataObjectList] {

  val tag: BerTag = "DFFF01"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}
