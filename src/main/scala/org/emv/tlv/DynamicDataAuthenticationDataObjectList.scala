package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DynamicDataAuthenticationDataObjectList  (val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = DynamicDataAuthenticationDataObjectList.tag

}

object DynamicDataAuthenticationDataObjectList extends EMVDOLSpec[DynamicDataAuthenticationDataObjectList] {

  val tag: BerTag = berTag"9F49"

  override val maxLength: Int = 252

  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parseDynamicDataAuthenticationDataObjectList: Parser[DynamicDataAuthenticationDataObjectList] =
    parseEMVBySpec(DynamicDataAuthenticationDataObjectList, parseDOL(_))

}