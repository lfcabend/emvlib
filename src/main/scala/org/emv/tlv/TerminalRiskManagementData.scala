package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TerminalRiskManagementData(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag = TerminalRiskManagementData.tag

}

object TerminalRiskManagementData extends EMVDefaultBinaryWithVarLengthSpec[TerminalRiskManagementData] {

  val tag: BerTag = berTag"9F1D"

  def parser = parseEMVBySpec(TerminalRiskManagementData, parseB(_))

  override val maxLength = 8
  override val minLength = 1
}