package org.emv.tlv

import com.neovisionaries.i18n.LanguageCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeaf, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/10/16.
  */
case class IssuerURL(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = IssuerURL.tag

}

object IssuerURL extends EMVAlphaNumericSpecialWithVarLengthSpec[ByteVector, IssuerURL] {

  val tag: BerTag = berTag"5F50"

  override val maxLength: Int = Int.MaxValue

  override val minLength: Int = 0
  override val max: Int = Int.MaxValue
  override val min: Int = 0


  def parser: Parser[IssuerURL] =
    parseEMVBySpec(IssuerURL, parseANS(IssuerURL)(_))

}

