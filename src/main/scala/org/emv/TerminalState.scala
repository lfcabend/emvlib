package org.emv

import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816.{AID, Select, SelectResponse}
import org.tlv.TLV.BerTLV

import scala.collection.immutable.List

/**
  * Created by lau on 7/6/16.
  */
case class TerminalState(val config: TerminalConfig, val transmissions: TransactionTransmissions) {

}

trait Transmission[C <: APDUCommand, CR <: APDUCommandResponse] {

  val command: C

  val response: CR

}

case class TransactionTransmissions(val selectPPSETransmission: Option[SelectTransmission] = None,
                                    val selectTransmission: Option[SelectTransmission] = None,
                                    val gpoTransmission: Option[GPOTransmission] = None,
                                    val readRecordsTransmission: Option[List[ReadRecordTransmission]] = None,
                                    val generateACTransmission: Option[GenerateACTransmission] = None)

case class SelectTransmission(val command: Select, val response: SelectResponse) extends Transmission[Select, SelectResponse]

case class GPOTransmission(val command: Select, val response: SelectResponse) extends Transmission[Select, SelectResponse]

case class ReadRecordTransmission(val command: Select, val response: SelectResponse) extends Transmission[Select, SelectResponse]

case class GenerateACTransmission(val command: Select, val response: SelectResponse) extends Transmission[Select, SelectResponse]


case class TerminalConfig()

trait TLVParameters {

  val tlv: List[BerTLV]

}

case class GeneralParameters(override val tlv: List[BerTLV]) extends TLVParameters

case class BrandParameters(val brandAid: AID, override val tlv: List[BerTLV]) extends TLVParameters
