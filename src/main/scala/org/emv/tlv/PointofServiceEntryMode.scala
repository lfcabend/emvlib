package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class PointofServiceEntryMode(override val value: Seq[Byte])
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = PointofServiceEntryMode.tag

}

object PointofServiceEntryMode extends EMVDefaultNumericWithLengthSpec[PointofServiceEntryMode] {

  val tag: BerTag = "9F39"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

}
