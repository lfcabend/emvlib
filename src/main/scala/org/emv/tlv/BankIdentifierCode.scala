package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/16/16.
  */
case class BankIdentifierCode(override val value:ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = AuthorisationCode.tag
  override val templates = Set(DirectoryDiscretionaryTemplate.tag,
    FileControlInformationIssuerDiscretionaryData.tag)
}

object BankIdentifierCode extends EMVDefaultBinaryWithVarLengthSpec[BankIdentifierCode] {

  val tag: BerTag = berTag"5F54"

  override val maxLength = 11

  override val minLength = 8

  def parser = parseEMVBySpec(BankIdentifierCode, parseB(_))


}
