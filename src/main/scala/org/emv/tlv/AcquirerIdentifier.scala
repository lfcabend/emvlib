package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

trait AcquirerIdentifierT extends EMVTLVLeaf with NumberTextable {

  override val tag = AccountType.tag

}

case class AcquirerIdentifier(override val value: ByteVector) extends AcquirerIdentifierT

trait AcquirerIdentifierSpec extends EMVDefaultNumericWithLengthSpec[AcquirerIdentifier] {

  val length = 6

  val tag = berTag"9F01"

  val min = 6

  val max = 11

}

object AcquirerIdentifier extends AcquirerIdentifierSpec {

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(AcquirerIdentifier, parseN(AcquirerIdentifier)(_))

}