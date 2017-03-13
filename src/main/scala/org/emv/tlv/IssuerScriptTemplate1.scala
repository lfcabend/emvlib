package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptTemplate1(constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = IssuerScriptTemplate1.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object IssuerScriptTemplate1 extends TemplateSpec[IssuerScriptTemplate1] {

  val tag: BerTag = berTag"71"

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  //  override def parseEMVTLVValue(length: Int): ApplicationTemplate.Parser[List[EMVTLVType]] = ???
  override val maxLength: Int = 252
  override val minLength: Int = 0

  def parser(parser: Parser[EMVTLVType]): Parser[IssuerScriptTemplate1] =
    parseEMVBySpec(IssuerScriptTemplate1, parseTemplateValue(parser)(IssuerScriptTemplate1)(_))

}
