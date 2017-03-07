package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class ShortFileIdentifier(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = ShortFileIdentifier.tag

  override val templates = Set(FileControlInformationProprietaryTemplate.tag)
}

object ShortFileIdentifier extends EMVDefaultBinaryWithLengthSpec[ShortFileIdentifier] {

  val tag = berTag"88"

  override val length = 1

  def parser = parseEMVBySpec(ShortFileIdentifier, parseB(_))



}