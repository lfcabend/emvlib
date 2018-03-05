package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerIdentificationNumber(override val value: ByteVector)
  extends EMVTLVLeafNTextable with TemplateTag {

  override val tag = IssuerIdentificationNumber.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)
}

object IssuerIdentificationNumber extends EMVDefaultNumericWithLengthSpec[IssuerIdentificationNumber] {

  val tag = berTag"42"

  val length = 3

  override val max = 6

  override val min = 6

  def parser = parseEMVBySpec(IssuerIdentificationNumber, parseB(_))


}
