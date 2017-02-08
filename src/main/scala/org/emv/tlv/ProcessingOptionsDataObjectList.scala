package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class ProcessingOptionsDataObjectList(val list: List[(BerTag, Int)])
  extends org.emv.tlv.EMVTLV.DOL {

  override val tag: BerTag = ProcessingOptionsDataObjectList.tag

}

object ProcessingOptionsDataObjectList extends EMVDOLSpec[ProcessingOptionsDataObjectList] {

  val tag: BerTag = berTag"9F38"

  override val maxLength: Int = 252

  override val minLength: Int = 0

  def parser: Parser[ProcessingOptionsDataObjectList] =
    parseEMVBySpec(ProcessingOptionsDataObjectList, parseDOL(_))


}