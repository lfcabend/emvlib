package org.emv.tlv

import com.neovisionaries.i18n.LanguageCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/10/16.
  */
case class IssuerURL(override val value: ByteVector)
  extends EMVTLVLeafTextable with TemplateTag {

  override val tag = IssuerURL.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)
}

object IssuerURL extends EMVAlphaNumericSpecialWithVarLengthSpec[ByteVector, IssuerURL] {

  val tag  = berTag"5F50"

  override val maxLength = Int.MaxValue

  override val minLength = 0
  override val max = Int.MaxValue
  override val min = 0

  def parser = parseEMVBySpec(IssuerURL, parseANS(IssuerURL)(_))

}

