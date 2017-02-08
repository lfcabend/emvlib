package org.emv.tlv

import java.nio.charset.StandardCharsets

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationLabel(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = ApplicationLabel.tag

}

object ApplicationLabel extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[ApplicationLabel] {

  val tag: BerTag = berTag"50"

  val max: Int = 16

  val min: Int = 1

  override val maxLength: Int = 16

  override val minLength: Int = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationLabel] =
    parseEMVBySpec(ApplicationLabel, parseANS(ApplicationLabel)(_))

}
