package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptTemplate2(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = IssuerScriptTemplate2.tag

  override val templateTags: Set[BerTag] = IssuerScriptTemplate1.templateTags

}

object IssuerScriptTemplate2 extends TemplateSpec[IssuerScriptTemplate2] {

  override lazy val length: Int = Int.MaxValue

  val tag: BerTag = "72"

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  override val templateTags: Set[BerTag] = Set()
}
