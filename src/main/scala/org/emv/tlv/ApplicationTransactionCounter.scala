package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/7/16.
  */
case class ApplicationTransactionCounter(override val value: ByteVector)
  extends EMVTLVLeaf with BinaryNumber {

  override val tag: BerTag = ApplicationTransactionCounter.tag

}

object ApplicationTransactionCounter extends EMVDefaultBinaryWithLengthSpec[ApplicationTransactionCounter] {

  val tag: BerTag = berTag"9F36"

  val length: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationTransactionCounter] =
    parseEMVBySpec(ApplicationTransactionCounter, parseB(_))

}