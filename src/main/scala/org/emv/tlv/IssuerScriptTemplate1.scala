package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptTemplate1(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = IssuerScriptTemplate1.tag

  override val templateTags: Set[BerTag] = IssuerScriptTemplate1.templateTags

}

object IssuerScriptTemplate1 extends TemplateSpec[IssuerScriptTemplate1] {

  override lazy val length: Int = Int.MaxValue

  val tag: BerTag = "71"

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  //  override def parseEMVTLVValue(length: Int): ApplicationTemplate.Parser[List[EMVTLVType]] = ???
  override val templateTags: Set[BerTag] = Set()
}
