package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class DefaultTransactionCertificateDataObjectList (val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = DefaultTransactionCertificateDataObjectList.tag

}

object DefaultTransactionCertificateDataObjectList extends EMVDOLSpec[DefaultTransactionCertificateDataObjectList] {

  val tag: BerTag = "DFFF02"

  override val maxLength: Int = 252

  override val minLength: Int = 0

}
