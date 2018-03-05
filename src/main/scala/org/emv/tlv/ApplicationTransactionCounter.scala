package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/7/16.
  */
case class ApplicationTransactionCounter(override val value: ByteVector)
  extends EMVTLVLeaf with BinaryNumber with TemplateTag {

  override val tag: BerTag = ApplicationTransactionCounter.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag)

}

object ApplicationTransactionCounter extends EMVDefaultBinaryWithLengthSpec[ApplicationTransactionCounter] {

  val tag: BerTag = berTag"9F36"

  val length: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationTransactionCounter, parseB(_))

}