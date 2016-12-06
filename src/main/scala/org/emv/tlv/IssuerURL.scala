package org.emv.tlv

import com.neovisionaries.i18n.LanguageCode
import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeaf, EMVTLVLeafTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerURL(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = IssuerURL.tag

}

object IssuerURL extends EMVAlphaNumericSpecialWithVarLengthSpec[Seq[Byte], IssuerURL] {

  val tag: BerTag = "5F50"

  override val maxLength: Int = Int.MaxValue

  override val minLength: Int = 0
  override val max: Int = Int.MaxValue
  override val min: Int = 0
}

