package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class ProcessingOptionsDataObjectList(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = ProcessingOptionsDataObjectList.tag

}

object ProcessingOptionsDataObjectList extends EMVDOLSpec[ProcessingOptionsDataObjectList] {

  val tag: BerTag = "9F38"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}