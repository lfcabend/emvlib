package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerCodeTableIndex (override val value: ByteVector)
  extends EMVTLVLeafNTextable with TextableNumber with TemplateTag {

  override val tag = IssuerCodeTableIndex.tag

  override val templates = Set(FileControlInformationProprietaryTemplate.tag)

}

object IssuerCodeTableIndex extends EMVDefaultNumericWithLengthSpec[IssuerCodeTableIndex] {

  val tag = berTag"9F11"

  val length = 1

  override val max = 2

  override val min = 2

  def parser = parseEMVBySpec(IssuerCodeTableIndex, parseB(_))

}

