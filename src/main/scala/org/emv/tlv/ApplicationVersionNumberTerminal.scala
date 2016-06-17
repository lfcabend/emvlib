package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/15/16.
  */
case class ApplicationVersionNumberTerminal(override val value:Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationVersionNumberTerminal.tag

}

object ApplicationVersionNumberTerminal extends EMVDefaultBinaryWithLengthSpec[ApplicationVersionNumberTerminal] {

  val tag: BerTag = "9F09"

  val length: Int = 2

}