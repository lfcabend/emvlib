package org.emv

import org.emv.tlv.{ApplicationIdentifier, ApplicationInterchangeProfile}
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816.{AID, Select, SelectResponse}
import scodec.bits.ByteVector
import scodec.bits._
import org.lau.tlv.ber._
import com.softwaremill.quicklens._
import org.emv.commands.{GPOCommand, GPOResponse, ReadRecordCommand, ReadRecordResponse}

import scalaz._
import scala.collection.immutable.List

/**
  * Created by lau on 7/6/16.
  */
case class TerminalState(val config: TerminalConfig,
                         val transientData: TLVParameters,
                         val transmissions: TransactionTransmissions) {


  def withSelectApplication(selectApplication: SelectTransmission) = this.modify(_.transmissions.selectTransmission).
    setTo(Some(selectApplication))


  def withSelectPPSE(selectPPSE: SelectTransmission) = this.modify(_.transmissions.selectPPSETransmission).
    setTo(Some(selectPPSE))

  def supportedBrands() = config.brandParameters.map(_.aid).toSet

  def getSelectedAID(): Option[AID] =
    for {
    st <- transmissions.selectTransmission
    r <- st.command
  } yield (r.aid)

  def terminalTLV(selectedBrand: AID): List[BerTLV] =
    config.generalConfig.tlv ++ config.getTlVForBrand(selectedBrand).getOrElse(Nil)

  def terminalTLV(): List[BerTLV] = getSelectedAID() match {
      case Some(x) => terminalTLV(x)
      case _       => config.generalConfig.tlv
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

case class GenerateACTransmission(override val command: Option[Select] = None,
                                  override val response: Option[SelectResponse] = None)
  extends Transmission[Select, SelectResponse]


case class TerminalConfig(val generalConfig: GeneralParameters, val brandParameters: List[BrandParameters] = Nil) {

  def getTlVForBrand(selectedBrand: AID): Option[List[BerTLV]] = brandParameters.
    filter(_.aid == selectedBrand).map(_.tlv).headOption

  def getConfigForBrand(selectedBrand: AID): Option[BrandParameters] = brandParameters.
    find(_.aid == selectedBrand)

  def withBrandParameters(aid: AID): TerminalConfig = withBrandParameters(aid, Nil)

  def withBrandParameters(aid: AID, tlv: List[BerTLV]): TerminalConfig = this.modify(_.brandParameters).
    using(_ :+ new BrandParameters(aid, tlv))

  def withTlvAddedToGeneralConfig(value: BerTLV): TerminalConfig = this.modify(_.generalConfig.tlv).
    using(_ :+ value)

  def filterBrandParametersByAID(brandParameters: BrandParameters, aid: AID): Boolean =
    brandParameters.aid == aid

  //TODO this shit creates some compiler crash
  //Error:scalac: Error while emitting TerminalState.scala
//  key not found: value aid
  //Warning:scalac: 	at scala.collection.MapLike.default(MapLike.scala:232)
  def withTlvAddedToBrandParameters(aid: AID, value: BerTLV): TerminalConfig = ???
//    this.modify(_.brandParameters.eachWhere(filterBrandParametersByAID(_, aid)).tlv).using(_ :+ value)

}

trait TLVParameters {

  val tlv: List[BerTLV]

}

case class GeneralParameters(override val tlv: List[BerTLV] = Nil) extends TLVParameters {


  def withAppendedTlv(value: BerTLV) =
    this.modify(_.tlv).using(_ :+ value)

}

case class BrandParameters(val aid: AID, override val tlv: List[BerTLV] = Nil) extends TLVParameters {

  def withAppendedTlv(value: BerTLV) =
    this.modify(_.tlv).using(_ :+ value)

}
