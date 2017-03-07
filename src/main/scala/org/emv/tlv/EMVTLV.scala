package org.emv.tlv

import java.nio.charset.StandardCharsets
import java.util.Currency

import com.neovisionaries.i18n.CountryCode
import org.iso7816._
import org.joda.time.{LocalDate, LocalTime}
import org.lau.tlv.ber._
import org.lau.tlv.ber.BerTLVParser
import scodec.bits.ByteVector

import scalaz.{-\/, \/, \/-}
import scala.language.postfixOps
import scala.util.Try
import scala.util.matching.Regex

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

  trait EMVDefaultBinaryWithLengthSpec[R <: EMVTLVType] extends EMVBinaryWithLengthSpec[ByteVector, R]

  trait EMVBinaryWithVarLengthSpec[V, R <: EMVTLVType] extends EMVBinarySpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultBinaryWithVarLengthSpec[R <: EMVTLVType] extends EMVBinaryWithVarLengthSpec[ByteVector, R]

  trait EMVNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.N

  }

  trait EMVNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVNumericSpec[V, R] with EMVLengthSpec

  trait EMVNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultNumericWithLengthSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[ByteVector, R]

  trait EMVDefaultNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVNumericWithVarLengthSpec[ByteVector, R]

  trait EMVAlphaSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.A

  }

  trait EMVAlphaWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaSpec[V, R] with EMVLengthSpec

  trait EMVAlphaWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaWithLengthSpec[R <: EMVTLVType] extends EMVAlphaWithLengthSpec[ByteVector, R]

  trait EMVDefaultAlphaWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaWithVarLengthSpec[ByteVector, R]

  trait EMVAlphaNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.AN

  }

  trait EMVAlphaNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpec[V, R] with EMVLengthSpec

  trait EMVAlphaNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaNumericWithLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericWithLengthSpec[ByteVector, R]

  trait EMVDefaultAlphaNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericWithVarLengthSpec[ByteVector, R]

  trait EMVAlphaNumericSpecialSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.ANS

    def apply(text: String) = new ApplicationLabel(ByteVector(text.getBytes(StandardCharsets.US_ASCII)))

  }

  trait EMVAlphaNumericSpecialWithLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpecialSpec[V, R] with EMVLengthSpec

  trait EMVDefaultAlphaNumericSpecialWithLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericSpecialWithLengthSpec[ByteVector, R]

  trait EMVAlphaNumericSpecialWithVarLengthSpec[V, R <: EMVTLVType] extends EMVAlphaNumericSpecialSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultAlphaNumericSpecialWithVarLengthSpec[R <: EMVTLVType] extends EMVAlphaNumericSpecialWithVarLengthSpec[ByteVector, R]

  trait EMVCompactNumericSpec[V, R <: EMVTLVType] extends EMVSpec[V, R] with DataValueVarLengthSpec {

    val valueDataType: ValueDataType.Value = ValueDataType.CN

  }

  trait EMVCompactNumericWithLengthSpec[V, R <: EMVTLVType] extends EMVCompactNumericSpec[V, R] with EMVLengthSpec

  trait EMVCompactNumericWithVarLengthSpec[V, R <: EMVTLVType] extends EMVCompactNumericSpec[V, R] with EMVVarLengthSpec

  trait EMVDefaultCompactNumericWithLengthSpec[R <: EMVTLVType] extends EMVCompactNumericWithLengthSpec[ByteVector, R]

  trait EMVDefaultCompactNumericWithVarLengthSpec[R <: EMVTLVType] extends EMVCompactNumericWithVarLengthSpec[ByteVector, R]

  trait EMVCurrencySpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[Currency, R] {

    val length: Int = 2

    val max: Int = 3

    val min: Int = 3

  }

  trait EMVCountryCodeSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[CountryCode, R] {

    val length: Int = 2

    val max: Int = 3

    val min: Int = 3

  }

  trait EMVCountryCodeSpecA2[R <: EMVTLVType] extends EMVAlphaSpec[CountryCode, R] {

    val length: Int = 2

    val max: Int = 2

    val min: Int = 2

  }

  trait EMVCountryCodeSpecA3[R <: EMVTLVType] extends EMVAlphaSpec[CountryCode, R] {

    val length: Int = 2

    val max: Int = 3

    val min: Int = 3

  }

  trait EMVDateSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[LocalDate, R] {

    val length: Int = 3

    val max: Int = 6

    val min: Int = 6

  }

  trait EMVTimeSpec[R <: EMVTLVType] extends EMVNumericWithLengthSpec[LocalTime, R] {

    val length: Int = 3

    val max: Int = 6

    val min: Int = 6

  }

  trait EMVAIDSpec[R <: EMVTLVType] extends EMVBinaryWithVarLengthSpec[AID, R] {

    val maxLength: Int = 16

    val minLength: Int = 5

  }

  trait EMVDOLSpec[R <: DOL] extends EMVBinaryWithVarLengthSpec[List[(BerTag, Int)], R]

  trait TemplateSpec[T <: EMVTLVCons] extends EMVSpec[List[BerTLV], T] with EMVBinaryWithVarLengthSpec[List[BerTLV], T] {

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

  trait EMVTLVLeafWithCountryCode extends EMVTLVLeaf with CountryCodeHelper

  trait EMVTLVLeafWithDate extends EMVTLVLeaf with DateHelper

  trait EMVTLVLeafWithTime extends EMVTLVLeaf with TimeHelper

  trait EMVTLVLeafTextable extends EMVTLVLeaf with Textable

  trait EMVTLVLeafNTextable extends EMVTLVLeaf with NumberTextable

  trait EMVTLVLeafCNTextable extends EMVTLVLeaf with CompactNumberTextable

  trait Template extends EMVTLVCons {

  }

  trait DOL extends EMVTLVLeaf {

    val value: ByteVector = list.map(
      { case (x, y) => x.value ++ BerTLV.encodeLength(y) }
    ).foldRight[ByteVector](ByteVector.empty)(_ ++ _)

    val list: List[(BerTag, Int)]

    def createDOLValue(tlvList: List[BerTLV]): ByteVector = {
      val m: Map[BerTag, BerTLV] = tlvList.map(t => t.tag -> t).toMap
      list.foldRight[ByteVector](ByteVector.empty)((x: (BerTag, Int), y: ByteVector) => {
        m.get(x._1) match {
          case Some(tagValue: BerTLV) if tagValue.length == x._2 => tagValue.value ++ y
          case _ => ByteVector(List.fill[Byte](x._2)(0.toByte)) ++ y
        }
      })
    }

    def createDOLValue(input: Map[BerTag, BerTLV]): \/[String, ByteVector] =
      list.foldRight[\/[String, ByteVector]](\/-(ByteVector.empty))((x: (BerTag, Int), y: \/[String, ByteVector]) => {
        y match {
          case \/-(r) => input.get(x._1) match {
            case Some(f) if f.length == x._2 => \/-(f.value ++ r)
            case Some(f) => -\/(s"Tag ${x._1} was found but has invalid length ${f.length}, should be ${x._2}")
            case None => -\/(s"Tag ${x._1} was NOT found")
          }
          case -\/(l) => -\/(l)
        }
      })

    override def toString: String =
      s"${super.toString}\n" + list.map(x => s"\t${x._1} ${BerTLV.encodeLength(x._2).toHex} (${x._2})").mkString("\n")

  }

  trait LeafToStringHelper {

    val value: ByteVector

    val label: String = this.getClass.getSimpleName

    val postFixLabel: Option[String] = None

    override def toString = {
      val post = if (postFixLabel.isEmpty) "" else s" ( ${postFixLabel.get} )"
      s"$label: ${value.toHex}$post"
    }

  }

  trait ConsToStringHelper {

    def value: ByteVector

    def constructedValue: List[BerTLV]

    def label: String = this.getClass.getSimpleName

    override def toString = {

      val lines = constructedValue.map(x => {
        x.toString().split("\n").map(y => s"\t$y\n").mkString
      }).mkString
      s"$label:\n$lines"
    }

  }

  object EMVTLVParser {

    import fastparse.byte.all._


    def parseLeafTag(tag: BerTag): Parser[BerTag] =
      P(BerTLVParser.parseNonConstructedATag.filter(x => {
        x == tag
      }).opaque{
        (s"Leaf Tag is not ${tag}")
      })


    def parseConsTag(tag: BerTag): Parser[BerTag] =
      P(BerTLVParser.parseAConstructedTag.filter(_ == tag).opaque(s"Cons Tag is not ${tag}"))

    def parseTag[V, R <: EMVTLVType](spec: EMVSpec[V, R]): Parser[BerTag] = {
      if (spec.tag.isConstructed) parseConsTag(spec.tag)
      else parseLeafTag(spec.tag)
    }

    def parseEMVBySpec[V, R <: EMVTLVType](spec: EMVSpec[V, R], valueParser: Int => Parser[V]): Parser[R] = P(for {
      t <- parseTag(spec)
      l <- parseLength(spec)
      v <- valueParser(l)
    } yield (spec(v)))

    def parseLength[V, R <: EMVTLVType](spec: EMVSpec[V, R]): Parser[Int] = spec match {
      case spec: EMVLengthSpec => P(parseLength(spec.length))
      case spec: EMVVarLengthSpec => P(parseVarLengthBetween(spec.minLength, spec.maxLength))
    }

    def parseLength(length: Int): Parser[Int] =
      P(BerTLVParser.parseLength.withFilter(_ == length).opaque(s"length should be ${length}"))

    def parseVarLengthBetween(min: Int, max: Int): Parser[Int] =
      P(BerTLVParser.parseLength.withFilter((x: Int) => x >= min && x <= max).
        opaque(s"length should be between min ${min} and max ${max}"))

    def parseA(spec: DataValueVarLengthSpec)(length: Int): Parser[ByteVector] =
      parseA(spec.min, spec.max)(length)

    def parseA(min: Int, max: Int)(length: Int): Parser[ByteVector] =
      parseEMVDataElement(length, s"^([a-z]|[A-Z]){${min},${max}}$$".r, "Alphabetic", toASCIIString)

    def parseCN(spec: DataValueVarLengthSpec)(length: Int): Parser[ByteVector] =
      parseA(spec.min, spec.max)(length)

    def parseCN(min: Int, max: Int)(length: Int): Parser[ByteVector] =
      parseEMVDataElement(length, s"^[0-9]{${min},${max}}F*$$".r, "Alphabetic", toASCIIString)

    def parseAN(spec: DataValueVarLengthSpec)(length: Int): Parser[ByteVector] =
      parseAN(spec.min, spec.max)(length)

    def parseAN(min: Int, max: Int)(length: Int): Parser[ByteVector] =
      parseEMVDataElement(length, s"^([a-z]|[A-Z]|[0-9]){${min},${max}}$$".r, "Alphabetic Numeric",
        toASCIIString)

    def toASCIIString = (x: ByteVector) => new String(x.toArray, "US-ASCII")

    def parseN(spec: DataValueVarLengthSpec)(length: Int): Parser[ByteVector] =
      parseN(spec.min, spec.max)(length)

    def parseN(min: Int, max: Int)(length: Int): Parser[ByteVector] =
      parseEMVDataElement(length, s"^0*[0-9]{${min},${max}}$$".r, "Numeric", _.toHex)

    def parseANS(spec: DataValueVarLengthSpec)(length: Int): Parser[ByteVector] =
      parseANS(spec.min, spec.max)(length)

    def parseANS(min: Int, max: Int)(length: Int): Parser[ByteVector] =
      P(AnyElem.!.rep(exactly = length).!.withFilter(x => x.length >= min && x.length <= max).
        opaque("Invalid length"))

    def parseEMVDataElement(length: Int, pattern: Regex, emvType: String,
                            toStringFunc: ByteVector => String): Parser[ByteVector] =
      P(BerTLVParser.repParsingForXByte[ByteVector](AnyElem.!, length).!.withFilter(ba => {
        val s: String = toStringFunc(ba)
        pattern.findFirstIn(s).isDefined
      }).opaque(s"value is not ${emvType}"))

    def parseB(length: Int): Parser[ByteVector] =
      P(AnyElem.!.rep(exactly = length).!)

    //TODO fix this in a more idiomatic way
    @unchecked
    def parseDate(length: Int): Parser[LocalDate] =
    P(AnyElem.!.rep(exactly = length).!.map(x => {
      Try {
        DateHelper.bytesToDate(x)
      }
    }).withFilter({
      case scala.util.Success(_) => true;
      case _ => false
    }).opaque("value is not a valid date").
      map({ case scala.util.Success(v) => v }))

    //TODO fix this in a more idiomatic way
    @unchecked
    def parseTime(length: Int): Parser[LocalTime] =
    P(AnyElem.!.rep(exactly = length).!.map(x => {
      Try {
        TimeHelper.bytesToTime(x)
      }
    }).withFilter({
      case scala.util.Success(_) => true;
      case _ => false
    }).opaque("value is not a valid date").
      map({ case scala.util.Success(v) => v }))

    //TODO fix this in a more idiomatic way
    @unchecked
    def parseCurrencyCode(length: Int): Parser[Currency] =
    P(parseN(3, 3)(length).map((x: ByteVector) => {
      val num: Int = x.toHex.toInt
      CurrencyHelper.getCurrencyInstance(num)
    }).withFilter({
      case Some(_) => true
      case _ => false
    }).opaque("number is not a currency code").map({
      case Some(x) => x
    }))

    //TODO fix this in a more idiomatic way
    @unchecked
    def parseCountryCode(length: Int): Parser[CountryCode] =
    P(parseN(3, 3)(length).map((x: ByteVector) => {
      val num: Int = x.toHex.toInt
      CountryCodeHelper.getCountryCodeInstance(num)
    }).withFilter({
      case Some(_) => true
      case _ => false
    }).opaque("number is not a country code").map({
      case Some(x) => x
    }))

    //TODO fix this in a more idiomatic way
    @unchecked
    def parseCountryCodeA(length: Int, numberChar: Int): Parser[CountryCode] =
    P(parseA(numberChar, numberChar)(length).map((x: ByteVector) => {
      val a2: String = toASCIIString(x)
      CountryCodeHelper.getCountryCodeInstance(a2)
    }).withFilter({
      case Some(_) => true
      case _ => false
    }).opaque("string is not a country code").map({
      case Some(x) => x
    }))

    def parseDOL(length: Int): Parser[List[(BerTag, Int)]] =
      P(BerTLVParser.repParsingForXByte(parseDOLItem, length))

    def parseDOLItem: Parser[(BerTag, Int)] = P(for {
      t <- BerTLVParser.parseTag
      l <- BerTLVParser.parseLength
    } yield ((t, l)))

    //    def parseDOLValue(list: List[(BerTag, Int)]): Parser[List[EMVTLVType]] = list match {
    //      case (x :: xs) => for {
    //        h <- parseDOLTLVValue(x)
    //        t <- parseDOLValue(xs)
    //      } yield (h :: t)
    //      case Nil => success(List())
    //    }

    //    def parseTemplateValue[T <: EMVTLVCons](template: TemplateSpec[T])(length: Int): Parser[List[EMVTLVType]] =
    //      BerTLVParser.repParsingForXByte[EMVTLVType](parseTemplateTag(template), length)

    def parseEMVTLV: Parser[EMVTLVType] = P(
      AccountType.parser | AcquirerIdentifier.parser | AdditionalTerminalCapabilities.parser | AmountAuthorized.parser |
        AmountOther.parser | AmountOtherBinary.parser | AmountReferenceCurrency.parser | ApplicationCryptogram.parser |
        ApplicationCurrencyCode.parser |
        ApplicationDedicatedFileName.parser | ApplicationEffectiveDate.parser | ApplicationExpirationDate.parser |
        ApplicationFileLocator.parser | ApplicationIdentifier.parser | ApplicationInterchangeProfile.parser |
        ApplicationLabel.parser | ApplicationPreferredName.parser | ApplicationPrimaryAccountNumber.parser|
        ApplicationPrimaryAccountNumberSequenceNumber.parser |
        ApplicationIdentifier.parser | ApplicationPriorityIndicator.parser | ApplicationReferenceCurrency.parser |
        ApplicationReferenceCurrencyExponent.parser | ApplicationTemplate.parser | ApplicationTransactionCounter.parser |
        ApplicationUsageControl.parser | ApplicationVersionNumber.parser | ApplicationVersionNumberTerminal.parser |
        AuthorisationCode.parser | AuthorisationResponseCode.parser | BankIdentifierCode.parser | CardholderName.parser |
        CardholderNameExtended.parser | CardholderVerificationMethodList.parser |
        CardholderVerificationMethodResults.parser | CardRiskManagementDataObjectList1.parser | CardRiskManagementDataObjectList2.parser |
        CertificationAuthorityPublicKeyIndex.parser | CertificationAuthorityPublicKeyIndexTerminal.parser | CommandTemplate.parser |
        CryptogramInformationData.parser | DataAuthenticationCode.parser |
        DirectoryDefinitionFileName.parser | DirectoryDiscretionaryTemplate.parser | DynamicDataAuthenticationDataObjectList.parser|
        DedicatedFileName.parser| FileControlInformationIssuerDiscretionaryData.parser |
        FileControlInformationProprietaryTemplate.parser | FileControlInformationTemplate.parser |
        ICCDynamicNumber.parser | InterfaceDeviceSerialNumber.parser |
        InternationalBankAccountNumber.parser | IssuerActionCodeDefault.parser |
        IssuerActionCodeDenial.parser | IssuerActionCodeOnline.parser | IssuerApplicationData.parser | IssuerAuthenticationData.parser |
        IssuerCodeTableIndex.parser | IssuerCountryCode.parser | IssuerCountryCodeA2.parser | IssuerCountryCodeA3.parser |
        IssuerIdentificationNumber.parser | IssuerPublicKeyCertificate.parser | IssuerPublicKeyExponent.parser |
        IssuerPublicKeyRemainder.parser | IssuerScriptCommand.parser | IssuerScriptIdentifier.parser |
        IssuerIdentificationNumber.parser |
        IssuerScriptTemplate1.parser | IssuerScriptTemplate2.parser | IssuerURL.parser | LanguagePreference.parser |
        LastOnlineApplicationTransactionCounterRegister.parser | LogEntry.parser | LogFormat.parser |
        LowerConsecutiveOfflineLimit.parser | MerchantCategoryCode.parser | MerchantIdentifier.parser |
        MerchantNameLocation.parser | PersonalIdentificationNumberTryCounter.parser | PointofServiceEntryMode.parser |
        ProcessingOptionsDataObjectList.parser | READRECORDResponseMessageTemplate.parser | ResponseMessageTemplateFormat1.parser |
        ResponseMessageTemplateFormat2.parser | ServiceCode.parser | ShortFileIdentifier.parser | SignedDynamicApplicationData.parser |
        SignedStaticApplicationData.parser | StaticDataAuthenticationTagList.parser | TerminalCapabilities.parser |
        TerminalCountryCode.parser | TerminalFloorLimit.parser | TerminalIdentification.parser | TerminalRiskManagementData.parser |
        TerminalType.parser | TerminalVerificationResults.parser | Track1DiscretionaryData.parser | Track2DiscretionaryData.parser |
        Track2EquivalentData.parser | TransactionCertificateDataObjectList.parser | TransactionCertificateHashValue.parser |
        TransactionCurrencyCode.parser | TransactionCurrencyExponent.parser | TransactionDate.parser |
        TransactionPersonalIdentificationNumberData.parser | TransactionReferenceCurrencyCode.parser |
        TransactionReferenceCurrencyExponent.parser | TransactionSequenceCounter.parser | TransactionStatusInformation.parser |
        TransactionTime.parser | TransactionType.parser | UnpredictableNumber.parser | UpperConsecutiveOfflineLimit.parser
    )

    def parseTemplateTag[T <: EMVTLVCons](template: TemplateSpec[T]): Parser[EMVTLVType] =
      parseEMVTLV.filter(y => y  match {
        case x: TemplateTag => x.templates.contains(template.tag)
        case _ => false
      })//.opaque(s"tag is not defined as part of template with tag ${template.tag}")

    def parseTemplateValue[T <: EMVTLVCons](template: TemplateSpec[T])(length: Int): Parser[List[EMVTLVType]] =
      BerTLVParser.repParsingForXByte[EMVTLVType](parseTemplateTag(template), length)

  }

}
