package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class ShortFileIdentifier(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = ShortFileIdentifier.tag

}

object ShortFileIdentifier extends EMVDefaultBinaryWithLengthSpec[ShortFileIdentifier] {

  val tag: BerTag = berTag"88"

  override val length: Int = 1

  def parser: Parser[ShortFileIdentifier] =
    parseEMVBySpec(ShortFileIdentifier, parseB(_))



}