package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/15/16.
  */
case class ApplicationVersionNumberTerminal(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationVersionNumberTerminal.tag

}

object ApplicationVersionNumberTerminal extends EMVDefaultBinaryWithLengthSpec[ApplicationVersionNumberTerminal] {

  val tag: BerTag = berTag"9F09"

  val length: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationVersionNumberTerminal] =
    parseEMVBySpec(ApplicationVersionNumberTerminal, parseB(_))

}