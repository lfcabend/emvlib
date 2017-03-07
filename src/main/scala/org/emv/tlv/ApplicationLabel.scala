package org.emv.tlv

import java.nio.charset.StandardCharsets

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationLabel(override val value: ByteVector)
  extends EMVTLVLeafTextable with TemplateTag {

  override val tag = ApplicationLabel.tag

  override val templates = Set(ApplicationTemplate.tag,
    FileControlInformationProprietaryTemplate.tag)
}

object ApplicationLabel extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[ApplicationLabel] {

  val tag = berTag"50"

  val max = 16

  val min = 1

  override val maxLength = 16

  override val minLength = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationLabel, parseANS(ApplicationLabel)(_))

}
