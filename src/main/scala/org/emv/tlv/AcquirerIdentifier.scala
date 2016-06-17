package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.tlv.HexUtils._
import org.tlv.TLV.{BerTag, BerTLVLeafT}

trait AcquirerIdentifierT extends EMVTLVLeaf with NumberTextable {

  override val tag: BerTag = AccountType.tag

}

case class AcquirerIdentifier(override val value: Seq[Byte]) extends AcquirerIdentifierT

trait AcquirerIdentifierSpec extends EMVDefaultNumericWithLengthSpec[AcquirerIdentifier] {

  val length = 6

  val tag: BerTag = "9F01"

  val min = 6

  val max = 11

}

object AcquirerIdentifier extends AcquirerIdentifierSpec