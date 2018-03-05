package org.emv.tlv

import java.nio.charset.StandardCharsets

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationPreferredName(override val value: ByteVector)
  extends EMVTLVLeafTextable with TemplateTag {

  override val tag: BerTag = ApplicationPreferredName.tag

  override val templates = Set(ApplicationTemplate.tag,
    FileControlInformationProprietaryTemplate.tag)
}

object ApplicationPreferredName extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[ApplicationPreferredName] {

  val tag: BerTag = berTag"9F12"

  val length: Int = 16

  val max: Int = 16

  val min: Int = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationPreferredName] =
    parseEMVBySpec(ApplicationPreferredName, parseANS(ApplicationPreferredName)(_))

  override val maxLength: Int = 16

  override val minLength: Int = 1

}

