package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptTemplate2(constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = IssuerScriptTemplate2.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object IssuerScriptTemplate2 extends TemplateSpec[IssuerScriptTemplate2] {

  val tag: BerTag = berTag"72"

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  override val maxLength: Int = 252
  override val minLength: Int = 0


  def parser: Parser[IssuerScriptTemplate2] =
    parseEMVBySpec(IssuerScriptTemplate2, parseTemplateValue(IssuerScriptTemplate2)(_))

}
