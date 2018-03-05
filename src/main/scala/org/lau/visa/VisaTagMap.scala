package org.lau.visa

import org.emv.tlv.EMVTLV
import org.lau.visa.dataelements.{TerminalTransactionQualifiers, _}

/**
  * Created by lau on 3/10/17.
  */
object VisaTagMap {

  val tagsMap = EMVTLV.tagsMap + (CardTransactionQualifiers.tag -> CardTransactionQualifiers) +
    (CustomerExclusiveData.tag -> CustomerExclusiveData) +
    (CardTransactionQualifiers.tag -> CardTransactionQualifiers) +
    (ApplicationProgramID.tag -> ApplicationProgramID) +
    (TokenRequesterID.tag -> TokenRequesterID) +
    (TerminalTransactionQualifiers.tag -> TerminalTransactionQualifiers)

}
