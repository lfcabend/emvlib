package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class ProcessingOptionsDataObjectList(val data: (List[(BerTag, Int)], Map[BerTag, EMVSpec[_, _]]))
  extends DOL with TemplateTag {

  def this(data: List[(BerTag, Int)]) {
    this((data, EMVTLV.tagsMap))
  }

  override val possibleTags = data._2

  override val list: List[(BerTag, Int)] = data._1

  override val tag = ProcessingOptionsDataObjectList.tag

  override val templates = Set(FileControlInformationProprietaryTemplate.tag)
}


object ProcessingOptionsDataObjectList extends
  EMVBinaryWithVarLengthSpec[(List[(BerTag, Int)], Map[BerTag, EMVSpec[_, _]]), ProcessingOptionsDataObjectList] {

  val tag = berTag"9F38"

  override val maxLength = 252

  override val minLength = 0

  def apply(data: List[(BerTag, Int)]) = new ProcessingOptionsDataObjectList(data, EMVTLV.tagsMap)

  def parser(possibleTags: Map[BerTag, EMVSpec[_, _]]) =
    parseEMVBySpec(ProcessingOptionsDataObjectList, parseDOL(_).map(x => {
      (x, possibleTags)
    }))

}