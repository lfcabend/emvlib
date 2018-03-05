package org.emv

import com.neovisionaries.i18n.CountryCode
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816.{AID, NormalProcessingNoFurtherQualification, Select, SelectResponse}
import org.lau.tlv.ber._
import com.softwaremill.quicklens._
import org.emv.commands._
import fastparse.byte.all._
import org.emv.tlv._
import org.emv.tlv.EMVTLV.EMVTLVType
import org.lau.visa.dataelements.TerminalTransactionQualifiers

import scalaz.Equal
import scalaz._

/**
  * Created by lau on 7/6/16.
  */
case class TerminalState(val config: TerminalConfig,
                         val transientData: TerminalTransientData,
                         val transmissions: TransactionTransmissions) {

  def withAmountAuthorized(amountAuthorized: AmountAuthorized) =
    this.modify(_.transientData.amountAuthorized).setTo(Some(amountAuthorized))


  def isCandidateListNotEmpty() = !transientData.candidateList.isEmpty

  def withAIDCandidateList(candidateList: List[AID]) = this.modify(_.transientData.candidateList).setTo(candidateList)

  def withSelectApplication(selectApplication: SelectTransmission) = this.modify(_.transmissions.selectTransmission).
    setTo(Some(selectApplication))


  def withSelectPPSE(selectPPSE: SelectTransmission) = this.modify(_.transmissions.selectPPSETransmission).
    setTo(Some(selectPPSE))

  def withGetProcessingOptions(gpoTransmission: GPOTransmission) = this.modify(_.transmissions.gpoTransmission).
    setTo(Some(gpoTransmission))

  def withReadRecordsTransmission(readRecordsTransmission: List[ReadRecordTransmission]) = this.modify(_.transmissions.readRecordsTransmission).
    setTo(Some(readRecordsTransmission))

  def addReadRecordsTransmission(readRecordTransmission: ReadRecordTransmission) = this.modify(_.transmissions.readRecordsTransmission).
    using(x => x match {
      case None => Some(List(readRecordTransmission))
      case Some(y) => Some(y ::: List(readRecordTransmission))
    })

  def supportedBrands() = config.brandParameters.map(_.aid).toSet

  def getBrandParser: Option[Parser[EMVTLVType]] =
    getBrandParser(getSelectedAID())

  def getBrandParser(aid: Option[AID]): Option[Parser[EMVTLVType]] =
    aid.flatMap(x => BrandParserMapping.mapping.get(x))

  def getSelectedAID(): Option[AID] =
    for {
      st <- transmissions.selectTransmission
      r <- st.command
    } yield (r.aid)

  def terminalTLV(selectedBrand: AID): List[BerTLV] =
    transientData.tlv ++ config.getTlVForBrand(selectedBrand).getOrElse(Nil) ++ config.generalConfig.tlv

  def terminalTLV(): List[BerTLV] = getSelectedAID() match {
    case Some(x) => terminalTLV(x)
    case _ => transientData.tlv ++ config.generalConfig.tlv
  }

  val isPPSESuccessful: Boolean = isResponseSuccessful(transmissions.selectPPSETransmission)

  val isSelectionSuccessful: Boolean = isResponseSuccessful(transmissions.selectTransmission)

  val isGPOSuccessful: Boolean = isResponseSuccessful(transmissions.gpoTransmission)

  val isReadRecordsSuccessful: Boolean =
    transmissions.readRecordsTransmission.map(_.map(
      x => isResponseSuccessful(Some(x))
    ).forall(identity)) match {
      case Some(x) => x
      case None => false
    }

  val isGenerateACSuccessful: Boolean = isResponseSuccessful(transmissions.generateACTransmission)

  def isResponseSuccessful[C <: APDUCommand, CR <: APDUCommandResponse](cr: Option[Transmission[C, CR]]): Boolean =
    cr.flatMap(_.response.map(_.statusWord == NormalProcessingNoFurtherQualification)) match {
      case Some(x) => x
      case None => false
    }

}

trait Transmission[C <: APDUCommand, CR <: APDUCommandResponse] {

  val command: Option[C]

  val response: Option[CR]

}

case class TransactionTransmissions(val selectPPSETransmission: Option[SelectTransmission] = None,
                                    val selectTransmission: Option[SelectTransmission] = None,
                                    val gpoTransmission: Option[GPOTransmission] = None,
                                    val readRecordsTransmission: Option[List[ReadRecordTransmission]] = None,
                                    val generateACTransmission: Option[GenerateACTransmission] = None)

case class SelectTransmission(override val command: Option[Select] = None,
                              override val response: Option[SelectResponse] = None)
  extends Transmission[Select, SelectResponse]

case class GPOTransmission(override val command: Option[GPOCommand] = None,
                           override val response: Option[GPOResponse] = None)
  extends Transmission[GPOCommand, GPOResponse]

case class ReadRecordTransmission(override val command: Option[ReadRecordCommand] = None,
                                  override val response: Option[ReadRecordResponse] = None)
  extends Transmission[ReadRecordCommand, ReadRecordResponse]

case class GenerateACTransmission(override val command: Option[GenerateACCommand] = None,
                                  override val response: Option[GenerateACResponse] = None)
  extends Transmission[GenerateACCommand, GenerateACResponse]


case class TerminalConfig(generalConfig: GeneralParameters, brandParameters: List[BrandParameters] = Nil) {

  def getTlVForBrand(selectedBrand: AID): Option[List[BerTLV]] =
    brandParameters.filter(_.aid == selectedBrand).map(_.tlv).headOption

  def getConfigForBrand(selectedBrand: AID): Option[BrandParameters] =
    brandParameters.find(_.aid == selectedBrand)

  def withBrandParameters(br: BrandParameters): TerminalConfig =
    this.modify(_.brandParameters).using(_ :+ br)


  def filterBrandParametersByAID(brandParameters: BrandParameters, aid: AID): Boolean =
    brandParameters.aid == aid

}

trait TLVParameters {

  val tlv: List[BerTLV]

}

case class TerminalTransientData(val candidateList: List[AID] = Nil,
                                 transactionDate : Option[TransactionDate] = None,
                                 amountAuthorized : Option[AmountAuthorized] = None,
                                 amountOther : Option[AmountOther] = None,
                                 tvr : Option[TerminalVerificationResults] = None ,
                                 txnType : Option[TransactionType] = None,
                                 un : Option[UnpredictableNumber] = None
                                ) extends TLVParameters {

  override val tlv: List[BerTLV] =
    (transactionDate :: amountAuthorized :: amountOther :: tvr :: txnType :: un :: Nil).flatten

}

case class GeneralParameters(terminalCountryCode: Option[TerminalCountryCode] = None,
                              txnCurr : Option[TransactionCurrencyCode] = None,
                              mnl: Option[MerchantNameLocation] = None) extends TLVParameters {

  override val tlv: List[BerTLV] =
    (terminalCountryCode :: txnCurr :: mnl :: Nil).flatten

}

abstract class BrandParameters(val aid: AID) extends TLVParameters {

  def getAid() = aid

}

case class VisaBrandParameters(override val aid: AID, ttq: TerminalTransactionQualifiers) extends BrandParameters(aid) {

  override val tlv: List[BerTLV] = List(ttq)

}