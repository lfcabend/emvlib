package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class PointofServiceEntryMode(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = PointofServiceEntryMode.tag

}

object PointofServiceEntryMode extends EMVDefaultNumericWithLengthSpec[PointofServiceEntryMode] {

  val tag: BerTag = berTag"9F39"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

  def parser: Parser[PointofServiceEntryMode] =
    parseEMVBySpec(PointofServiceEntryMode, parseB(_))


}
