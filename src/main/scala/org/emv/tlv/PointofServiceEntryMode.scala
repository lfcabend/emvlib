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

  override val tag = PointofServiceEntryMode.tag

}

object PointofServiceEntryMode extends EMVDefaultNumericWithLengthSpec[PointofServiceEntryMode] {

  val tag = berTag"9F39"

  val length = 1

  override val max = 2

  override val min = 2

  def parser = parseEMVBySpec(PointofServiceEntryMode, parseB(_))


}
