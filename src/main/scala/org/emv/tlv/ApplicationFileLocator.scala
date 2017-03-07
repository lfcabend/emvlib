package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationFileLocator(val entries: List[AFLEntry])
  extends EMVTLVLeaf with TemplateTag {

  val value: ByteVector = entries.foldRight[ByteVector](ByteVector.empty)((x, y) => x.value ++ y)

  override val tag: BerTag = ApplicationFileLocator.tag

  override def toString: String = {

    s"${super.toString}\n" + entries.map(x => s"${x.toString}").mkString("\n")

  }

  override val templates = Set(ResponseMessageTemplateFormat2.tag)

  /**
    * tuple of sfi, recordNumber
    */
  val allRecords: List[(Byte, Byte)] = entries.flatMap(x => x.allRecords)

}

case class AFLEntry(b1: Byte, b2: Byte, b3: Byte, b4: Byte) {

  require(b2 != 0)
  require(b2 <= b3)
    s"last: ${lastRecord}, oda: ${nrODARecords}"

  def sfi: Int = b1 >> 3

  def firstRecord: Int = b2

  def lastRecord: Int = b3

  def nrODARecords: Int = b4

  def allRecords: List[(Byte, Byte)]  = Range(firstRecord, lastRecord + 1).map(x => (sfi.toByte, x.toByte)).toList

  def value: ByteVector = ByteVector(b1 :: b2 :: b3 :: b4 :: Nil)

}

object ApplicationFileLocator extends EMVBinaryWithVarLengthSpec[List[AFLEntry], ApplicationFileLocator] {

  val tag: BerTag = berTag"94"

  val length: Int = 252

  override val maxLength: Int = 252

  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationFileLocator] =
    parseEMVBySpec(ApplicationFileLocator, parseAFLEntries(_))


  def parseAFLEntries(l: Int): Parser[List[AFLEntry]] =
    parseB(l).map(x => {
      val l: List[ByteVector] = x.grouped(4).toList
      l.map(y => AFLEntry(y(0), y(1), y(2), y(3)))
    })


}
