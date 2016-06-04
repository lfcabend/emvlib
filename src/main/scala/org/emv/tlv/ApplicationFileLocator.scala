package org.emv.tlv

import org.emv.tlv.EMVTLV.LeafToStringHelper
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationFileLocator(val entries: List[AFLEntry])
  extends BerTLVLeafT with LeafToStringHelper {

  val value: Seq[Byte] = entries.foldRight(Nil: Seq[Byte])(_.value ++ _)

  override def tag(): BerTag = ApplicationFileLocator.tag

  override def toString: String = {

    s"${super.toString}\n" + entries.map(x => s"${x.toString}").mkString("\n")

  }

}

case class AFLEntry(b1: Byte, b2: Byte, b3: Byte, b4: Byte) {

  require(b2 != 0)
  require(b2 <= b3)
  require(b1 > 0 && b2 >= 0 && b3 >= 0 && b4 >=0)

  override def toString: String = s"\tsfi: ${sfi}, first: ${firstRecord}, " +
    s"last: ${lastRecord}, oda: ${nrODARecords}"

  def sfi: Int = b1 >> 3

  def firstRecord: Int = b2

  def lastRecord: Int = b3

  def nrODARecords: Int = b4

  def value: Seq[Byte] = b1 :: b2 :: b3 :: b4 :: Nil
}

object ApplicationFileLocator {

  def tag: BerTag = "94"

}
