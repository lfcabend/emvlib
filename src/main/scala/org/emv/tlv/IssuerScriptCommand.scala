package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
//TODO i think payload can be a APDUCommand
case class IssuerScriptCommand  (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerScriptCommand.tag

}

object IssuerScriptCommand extends EMVDefaultBinaryWithVarLengthSpec[IssuerScriptCommand] {

  val tag: BerTag = "86"

  override val maxLength: Int = 261

  override val minLength: Int = 0

}

