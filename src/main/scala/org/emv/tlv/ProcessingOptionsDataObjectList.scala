package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class ProcessingOptionsDataObjectList(val list: List[(BerTag, Int)])
  extends DOL with TemplateTag {

  override val tag = ProcessingOptionsDataObjectList.tag

  override val templates = Set(FileControlInformationProprietaryTemplate.tag)
}

object ProcessingOptionsDataObjectList extends EMVDOLSpec[ProcessingOptionsDataObjectList] {

  val tag = berTag"9F38"

  override val maxLength = 252

  override val minLength = 0

  def parser = parseEMVBySpec(ProcessingOptionsDataObjectList, parseDOL(_))


}