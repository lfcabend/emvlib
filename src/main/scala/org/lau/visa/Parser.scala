package org.lau.visa

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.ProcessingOptionsDataObjectList
import org.lau.visa.dataelements._
import org.lau.visa.dataelements.vcbp._

/**
  * Created by lau on 3/8/17.
  */
object Parser {

  def parseEMVTLV: Parser[EMVTLVType] = P(ApplicationProgramID.parser |
    CardTransactionQualifiers.parser | FormFactorIndicator.parser | CustomerExclusiveData.parser | TokenRequesterID.parser |
    CardAuthenticationRelatedData.parser | TerminalTransactionQualifiers.parser |
    ProcessingOptionsDataObjectList.parser(VisaTagMap.tagsMap) | EMVTLVParser.parseEMVTLV(org.lau.visa.Parser.parseEMVTLV))

}
