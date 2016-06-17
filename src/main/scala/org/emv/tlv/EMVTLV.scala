package org.emv.tlv

import java.nio.charset.StandardCharsets
import java.util.Currency
import org.emv.tlv.EMVTLV.EMVBinaryWithVarLengthSpec
import org.iso7816.AID
import org.joda.time.LocalDate
import org.tlv.{BinaryParsers, HexUtils}
import org.tlv.TLV._

import scala.util.Try
import scala.util.matching.Regex
import scalaz.{\/-, -\/, \/}

/**
  * Created by Lau on 5/22/2016.
  */
object EMVTLV {


  trait EMVSpec[V, R <: EMVTLVType] extends LengthSpec {

    val tag: BerTag

    val valueDataType: ValueDataType.Value

    def apply(v: V): R

  }


  trait LeafSpec[V, T <: EMVTLVType] extends EMVSpec[V, T]

  trait ConsSpec[V, T <: EMVTLVType] extends EMVSpec[V, T]

  trait LengthSpec

  trait EMVLengthSpec extends LengthSpec {

    val length: Int

  }

  trait DataValueVarLengthSpec extends LengthSpec {

    val max: Int

    val min: Int

  }

  trait EMVVarLengthSpec {

    val maxLength: Int

    val minLength: Int

  }

  trait EMVBinarySpec[V, R <: EMVTLVType] extends EMVSpec[V, R] {

    val valueDataType: ValueDataType.Value = ValueDataType.B

  }

  trait EMVBinaryWithLengthSpec[V, R <: EMVTLVType] extends EMVBinarySpec[V, R] with EMVLengthSpec

  trait EMVDefaultBinaryWithLengthSpec[R <: EMVTLVType] extends EMVBinaryWithLengthSpec[Seq[Byte], R]

  trait EMVBinaryWithVarLengthSpec[V, R <: EMVTLVType] extends EMVBinarySpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultBinaryWithVarLengthSpec[R <: EMVTLVType] extends EMVBinaryWithVarLengthSpec[Seq[Byte], R]

  trait EMVNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.N

  }

  trait EMVNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVNumericSpec[V, R] with EMVLengthSpec

  trait EMVNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultNumericWithLengthSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[Seq[Byte], R]

  trait EMVDefaultNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVNumericWithVarLengthSpec[Seq[Byte], R]

  trait EMVAlphaSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.A

  }

  trait EMVAlphaWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaSpec[V, R] with EMVLengthSpec

  trait EMVAlphaWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaWithLengthSpec[R <: EMVTLVType] extends EMVAlphaWithLengthSpec[Seq[Byte], R]

  trait EMVDefaultAlphaWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaWithVarLengthSpec[Seq[Byte], R]

  trait EMVAlphaNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.AN

  }

  trait EMVAlphaNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpec[V, R] with EMVLengthSpec

  trait EMVAlphaNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaNumericWithLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericWithLengthSpec[Seq[Byte], R]

  trait EMVDefaultAlphaNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericWithVarLengthSpec[Seq[Byte], R]

  trait EMVAlphaNumericSpecialSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.ANS

    def apply(text: String) = new ApplicationLabel(text.getBytes(StandardCharsets.US_ASCII))

  }

  trait EMVAlphaNumericSpecialWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpecialSpec[V, R] with EMVLengthSpec

  trait EMVDefaultAlphaNumericSpecialWithLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericSpecialWithLengthSpec[Seq[Byte], R]

  trait EMVAlphaNumericSpecialWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpecialSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaNumericSpecialWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericSpecialWithVarLengthSpec[Seq[Byte], R]

  trait EMVCompactNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.CN

  }

  trait EMVCompactNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVCompactNumericSpec[V, R] with EMVLengthSpec

  trait EMVCompactNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVCompactNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultCompactNumericWithLengthSpec[R <: EMVTLVType] extends EMVCompactNumericWithLengthSpec[Seq[Byte], R]

  trait EMVDefaultCompactNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVCompactNumericWithVarLengthSpec[Seq[Byte], R]

  trait EMVCurrencySpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[Currency, R] {

    val length: Int = 2

    val max: Int = 3

    val min: Int = 3

  }

  trait EMVDateSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[LocalDate, R] {

    val length: Int = 3

    val max: Int = 6

    val min: Int = 6

  }

  trait EMVAIDSpec[R <: EMVTLVType] extends EMVBinaryWithVarLengthSpec[AID, R] {

    val maxLength: Int = 16

    val minLength: Int = 5

  }

  trait EMVDOLSpec[R <: DOL] extends EMVBinaryWithVarLengthSpec[List[(BerTag, Int)], R]

  trait TemplateSpec[T <: EMVTLVCons] extends EMVSpec[List[EMVTLVType], T] with EMVLengthSpec {

    val templateTags: Set[BerTag]

  }

  trait TemplateTag extends BerTLV {

    val templates: Set[BerTag]

  }

  object ValueDataType extends Enumeration {
    val B, A, AN, ANS, N, CN = Value
  }

  trait EMVTLVType extends BerTLV {

    val tag: BerTag

    val length: Int

  }

  trait EMVTLVLeaf extends EMVTLVType with BerTLVLeafT with LeafToStringHelper

  trait EMVTLVCons extends EMVTLVType with BerTLVConsT with ConsToStringHelper

  trait EMVTLVLeafWithCurrency extends EMVTLVLeaf with CurrencyHelper

  trait EMVTLVLeafWithDate extends EMVTLVLeaf with DateHelper

  trait EMVTLVLeafTextable extends EMVTLVLeaf with Textable

  trait EMVTLVLeafNTextable extends EMVTLVLeaf with NumberTextable

  trait EMVTLVLeafCNTextable extends EMVTLVLeaf with CompactNumberTextable

  trait Template extends EMVTLVCons {

    val templateTags: Set[BerTag]

  }

  trait DOL extends EMVTLVLeaf {

    val value: Seq[Byte] = list.map(
      { case (x, y) => x.value.toList ++ BerTLV.encodeLength(y) }
    ).foldRight[List[Byte]](Nil)(_ ++ _)

    val list: List[(BerTag, Int)]

    def createDOLValue(input: Map[BerTag, EMVTLVType]): \/[String, Seq[Byte]] =
      list.foldRight[\/[String, Seq[Byte]]](\/-(Nil))((x: (BerTag, Int), y: \/[String, Seq[Byte]]) => {
        y match {
          case \/-(r) => input.get(x._1) match {
            case Some(f) if f.length == x._2 => \/-(f.value ++ r)
            case Some(f) => -\/(s"Tag ${x._1} was found but has invalid length ${f.length}, should be ${x._2}")
            case None => -\/(s"Tag ${x._1} was NOT found")
          }
          case -\/(l) => -\/(l)
        }
      })
  }

  trait LeafToStringHelper {

    val value: Seq[Byte]

    val label: String = this.getClass.getSimpleName

    val postFixLabel: Option[String] = None

    override def toString = {
      val post = if (postFixLabel.isEmpty) "" else s" ( ${postFixLabel.get} )"
      s"${label}: ${HexUtils.toHex(value)}${post}"
    }

  }

  trait ConsToStringHelper {

    def value: Seq[Byte]

    def constructedValue: List[BerTLV]

    def label: String = this.getClass.getSimpleName

    override def toString = {

      val lines = constructedValue.map(x => {
        x.toString().split("\n").map(y => s"\t${y}\n").mkString
      }).mkString
      s"${label}:\n${lines}"
    }

  }

  def parseEMVTLV(in: String) = EMVParser.parseEMVTLV(in)

  object EMVParser extends EMVParsers

  trait EMVParsers extends TLVParsers {

    def parseEMVBySpec[V, R <: EMVTLVType](spec: EMVSpec[V, R], valueParser: Int => Parser[V]): Parser[R] = for {
      t <- parseTag(spec)
      l <- parseLength(spec)
      v <- valueParser(l)
    } yield (spec(v))

    def parseTag[V, R <: EMVTLVType](spec: EMVSpec[V, R]): Parser[BerTag] =
      if (spec.tag.isConstructed) parseConsTag(spec.tag)
      else parseLeafTag(spec.tag)

    def parseLength[V, R <: EMVTLVType](spec: EMVSpec[V, R]): Parser[Int] = spec match {
      case spec: EMVLengthSpec => parseLength(spec.length)
      case spec: EMVVarLengthSpec => parseVarLengthBetween(spec.minLength, spec.maxLength)
    }

    def parseA(spec: DataValueVarLengthSpec)(length: Int): Parser[Seq[Byte]] =
      parseA(spec.min, spec.max)(length)

    def parseA(min: Int, max: Int)(length: Int): Parser[Seq[Byte]] =
      parseEMVDataElement(length, s"^([a-z]|[A-Z]){${min},${max}}$$".r, "Alphabetic", toASCIIString)

    def parseCN(spec: DataValueVarLengthSpec)(length: Int): Parser[Seq[Byte]] =
      parseA(spec.min, spec.max)(length)

    def parseCN(min: Int, max: Int)(length: Int): Parser[Seq[Byte]] =
      parseEMVDataElement(length, s"^[0-9]{${min},${max}}F*$$".r, "Alphabetic", toASCIIString)


    def parseAN(spec: DataValueVarLengthSpec)(length: Int): Parser[Seq[Byte]] =
      parseAN(spec.min, spec.max)(length)

    def parseAN(min: Int, max: Int)(length: Int): Parser[Seq[Byte]] =
      parseEMVDataElement(length, s"^([a-z]|[A-Z]|[0-9]){${min},${max}}$$".r, "Alphabetic Numeric",
        toASCIIString)

    def toASCIIString = (x: Seq[Byte]) => new String(x.toArray, "US-ASCII")

    def parseN(spec: DataValueVarLengthSpec)(length: Int): Parser[Seq[Byte]] =
      parseN(spec.min, spec.max)(length)

    def parseN(min: Int, max: Int)(length: Int): Parser[Seq[Byte]] =
      parseEMVDataElement(length, s"^0*[0-9]{${min},${max}}$$".r, "Numeric", HexUtils.toHex(_))

    def parseANS(spec: DataValueVarLengthSpec)(length: Int): Parser[Seq[Byte]] =
      parseANS(spec.max, spec.min)(length)

    def parseANS(min: Int, max: Int)(length: Int): Parser[Seq[Byte]] =
      repN(length, parseSingleByte).withFilter(x => x.length >= min && x.length <= max).
        withFailureMessage("Invalid length")

    def parseEMVDataElement(length: Int, pattern: Regex, emvType: String, toStringFunc: Seq[Byte] => String): Parser[Seq[Byte]] =
      repN(length, parseSingleByte).withFilter({ ba: Seq[Byte] =>
        val s: String = toStringFunc(ba)
        pattern.findFirstIn(s).isDefined
      }).withFailureMessage(s"value is not ${emvType}")

    def parseB(length: Int): Parser[List[Byte]] =
      repN(length, parseSingleByte)

    def parseAccountType: Parser[AccountType] = parseEMVBySpec(AccountType, parseN(AccountType)(_))

    def parseAcquirerIdentifier: Parser[AcquirerIdentifier] = parseEMVBySpec(AcquirerIdentifier, parseN(AcquirerIdentifier)(_))

    def parseAdditionalTerminalCapabilities: Parser[AdditionalTerminalCapabilities] =
      parseEMVBySpec(AdditionalTerminalCapabilities, parseB(_))

    def parseAmountAuthorized: Parser[AmountAuthorized] =
      parseEMVBySpec(AmountAuthorized, parseN(AmountAuthorized)(_))

    def parseAmountOtherBinary: Parser[AmountOtherBinary] = parseEMVBySpec(AmountOtherBinary, parseB(_))

    def parseAmountOther: Parser[AmountOther] =
      parseEMVBySpec(AmountOther, parseN(AmountOther)(_))

    def parseAmountReferenceCurrency: Parser[AmountReferenceCurrency] =
      parseEMVBySpec(AmountReferenceCurrency, parseB(_))

    def parseApplicationCryptogram: Parser[ApplicationCryptogram] =
      parseEMVBySpec(ApplicationCryptogram, parseB(_))

    def parseApplicationExpirationDate: Parser[ApplicationExpirationDate] =
      parseEMVBySpec(ApplicationExpirationDate, parseDate(_))

    def parseApplicationEffectiveDate: Parser[ApplicationEffectiveDate] =
      parseEMVBySpec(ApplicationEffectiveDate, parseDate(_))

    def parseApplicationDedicatedFileName: Parser[ApplicationDedicatedFileName] =
      parseEMVBySpec(ApplicationDedicatedFileName, parseB(_).map(AID(_)))

    def parseApplicationIdentifier: Parser[ApplicationIdentifier] =
      parseEMVBySpec(ApplicationIdentifier, parseB(_).map(AID(_)))

    def parseApplicationInterchangeProfile: Parser[ApplicationInterchangeProfile] =
      parseEMVBySpec(ApplicationInterchangeProfile, parseB(_))

    def parseApplicationLabel: Parser[ApplicationLabel] =
      parseEMVBySpec(ApplicationLabel, parseANS(ApplicationLabel)(_))

    def parseApplicationPrimaryAccountNumber: Parser[ApplicationPrimaryAccountNumber] =
      parseEMVBySpec(ApplicationPrimaryAccountNumber, parseN(ApplicationPrimaryAccountNumber)(_))

    def parseApplicationPrimaryAccountNumberSequenceNumber: Parser[ApplicationPrimaryAccountNumberSequenceNumber] =
      parseEMVBySpec(ApplicationPrimaryAccountNumberSequenceNumber, parseCN(ApplicationPrimaryAccountNumberSequenceNumber)(_))

    def parseApplicationPriorityIndicator: Parser[ApplicationPriorityIndicator] =
      parseEMVBySpec(ApplicationPriorityIndicator, parseB(_))

    def parseApplicationCurrencyCode: Parser[ApplicationCurrencyCode] =
      parseEMVBySpec(ApplicationCurrencyCode, parseCurrencyCode(_))

    def parseApplicationTransactionCounter: Parser[ApplicationTransactionCounter] =
      parseEMVBySpec(ApplicationTransactionCounter, parseB(_))

    def parseApplicationTemplate: Parser[ApplicationTemplate] =
      parseEMVBySpec(ApplicationTemplate, parseTemplateValue(ApplicationTemplate)(_))

    def parseApplicationUsageControl: Parser[ApplicationUsageControl] =
      parseEMVBySpec(ApplicationUsageControl, parseB(_))

    def parseApplicationVersion: Parser[ApplicationVersionNumber] =
      parseEMVBySpec(ApplicationVersionNumber, parseB(_))

    def parseApplicationReferenceCurrency: Parser[ApplicationReferenceCurrency] =
      parseEMVBySpec(ApplicationReferenceCurrency, (length: Int) => repN((length / 2), parseCurrencyCode(2)))

    def parseApplicationVersionNumberTerminal: Parser[ApplicationVersionNumberTerminal] =
      parseEMVBySpec(ApplicationVersionNumberTerminal, parseB(_))

    def parseAuthorisationCode: Parser[AuthorisationCode] =
      parseEMVBySpec(AuthorisationCode, parseB(_))

    def parseCardRiskManagementDataObjectList1: Parser[CardRiskManagementDataObjectList1] =
      parseEMVBySpec(CardRiskManagementDataObjectList1, parseDOL(_))

    def parseAID(length: Int): Parser[AID] = parseB(length).map(AID(_))

    def parseDate(length: Int): Parser[LocalDate] =
      repN(length, parseSingleByte).map(x => {
        Try {
          DateHelper.bytesToDate(x)
        }
      }).withFilter({
        case scala.util.Success(_) => true;
        case _ => false
      }).withFailureMessage("value is not a valid date").
        map({ case scala.util.Success(v) => v })

    def parseCurrencyCode(length: Int): Parser[Currency] =
      parseN(3, 3)(length).map((x: Seq[Byte]) => {
        val num: Int = HexUtils.toHex(x).toInt
        CurrencyHelper.getCurrencyInstance(num)
      }).withFilter({
        case Some(_) => true
        case _ => false
      }).withFailureMessage("number is not a currency code").map({
        case Some(x) => x
      })

    def parseTemplateTag[T <: EMVTLVCons](template: TemplateSpec[T]): Parser[EMVTLVType] = parseEMVTLV.
      filter(x => {
        template.templateTags.contains(x.tag)
      }).
      withFailureMessage(s"tag is not defined as part of template with tag: ${template.tag}")

    def parseTemplateValue[T <: EMVTLVCons](template: TemplateSpec[T])(length: Int): Parser[List[EMVTLVType]] =
      repParsingForXByte[EMVTLVType](length, parseTemplateTag(template))


    def parseApplicationFileLocator: Parser[ApplicationFileLocator] =
      parseEMVBySpec(ApplicationFileLocator, parseAFLEntries(_))

    def parseAFLEntries(l: Int): Parser[List[AFLEntry]] =
      parseB(l).map(x => {
        val l: List[Seq[Byte]] = x.grouped(4).toList
        l.map(y => AFLEntry(y(0), y(1), y(2), y(3)))
      })

    def parseDOL(length: Int): Parser[List[(BerTag, Int)]] =
      repParsingForXByte(length, parseDOLItem)

    def parseDOLItem: Parser[(BerTag, Int)] = for {
      t <- parseATag
      l <- parseALength
    } yield((t, l))

    def parseDOLValue(list: List[(BerTag, Int)]): Parser[List[EMVTLVType]] = list match {
      case (x :: xs) => for {
        h <- parseDOLTLVValue(x)
        t <- parseDOLValue(xs)
      } yield (h :: t)
      case Nil => success(List())
    }

    //TODO
    def parseDOLTLVValue(x: (BerTag, Int)): Parser[EMVTLVType] = ???

    def parseEMVTLV: Parser[EMVTLVType] = parseAccountType | parseAcquirerIdentifier |
      parseAdditionalTerminalCapabilities | parseAmountAuthorized | parseAmountOtherBinary |
      parseAmountOther | parseAmountReferenceCurrency | parseApplicationCryptogram |
      parseApplicationFileLocator | parseApplicationExpirationDate | parseApplicationIdentifier |
      parseApplicationDedicatedFileName | parseApplicationInterchangeProfile | parseApplicationLabel |
      parseApplicationPrimaryAccountNumber | parseApplicationPrimaryAccountNumberSequenceNumber |
      parseApplicationPriorityIndicator | parseApplicationReferenceCurrency | parseApplicationTemplate |
      parseApplicationTransactionCounter | parseApplicationUsageControl | parseApplicationVersion |
      parseApplicationVersionNumberTerminal | parseAuthorisationCode | parseCardRiskManagementDataObjectList1


    def parseEMVTLV(in: String): ParseResult[EMVTLVType] = parse(parseEMVTLV, in)

  }

}
