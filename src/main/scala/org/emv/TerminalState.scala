package org.emv

import org.emv.tlv.{ApplicationIdentifier, ApplicationInterchangeProfile}
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816.{AID, Select, SelectResponse}
import org.tlv.TLV.BerTLV
import com.softwaremill.quicklens._
import scalaz._

import scala.collection.immutable.List

/**
  * Created by lau on 7/6/16.
  */
case class TerminalState(val config: TerminalConfig,
                         val transientData: TLVParameters,
                         val transmissions: TransactionTransmissions) {

  def withSelectPPSE(selectPPSE: SelectTransmission) = this.modify(_.transmissions.selectPPSETransmission).
    setTo(Some(selectPPSE))

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

case class GPOTransmission(override val command: Option[Select] = None,
                           override val response: Option[SelectResponse] = None)
  extends Transmission[Select, SelectResponse]

case class ReadRecordTransmission(override val command: Option[Select] = None,
                                  override val response: Option[SelectResponse] = None)
  extends Transmission[Select, SelectResponse]

case class GenerateACTransmission(override val command: Option[Select] = None,
                                  override val response: Option[SelectResponse] = None)
  extends Transmission[Select, SelectResponse]


case class TerminalConfig(val generalConfig: GeneralParameters, val brandParameters: List[BrandParameters] = Nil) {

  def withBrandParameters(aid: AID): TerminalConfig = withBrandParameters(aid, Nil)

  def withBrandParameters(aid: AID, tlv: List[BerTLV]): TerminalConfig = this.modify(_.brandParameters).
    using(_ :+ new BrandParameters(aid, tlv))

  def withTlvAddedToGeneralConfig(value: BerTLV): TerminalConfig = this.modify(_.generalConfig.tlv).
    using(_ :+ value)

  def filterBrandParametersByAID(brandParameters: BrandParameters, aid: AID): Boolean =
    brandParameters.aid == aid

  def withTlvAddedToBrandParameters(aid: AID, value: BerTLV): TerminalConfig =
    this.modify(_.brandParameters.eachWhere(filterBrandParametersByAID(_, aid)).tlv).using(_ :+ value)

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
