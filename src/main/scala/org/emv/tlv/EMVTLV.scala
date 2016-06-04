package org.emv.tlv

import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag, TLVParsers}

import scala.util.matching.Regex

/**
  * Created by Lau on 5/22/2016.
  */
object EMVTLV {


  trait LeafToStringHelper {

    def value: Seq[Byte]

    def label: String = this.getClass.getSimpleName

    def postFixLabel: Option[String] = None

    override def toString = {
      val post = if (postFixLabel.isEmpty) "" else s" ( ${postFixLabel.get} )"
      s"${label}: ${HexUtils.toHex(value)}${post}"
    }

  }

  trait EMVParsers extends TLVParsers {

    def parseLeafwithTag[T <: BerTLV](tag: BerTag, len: Int,
                                      valueParser: Int => Parser[Seq[Byte]],
                                      cons: Seq[Byte] => T): Parser[T] =
      for {
        t <- parseLeafTag(tag)
        length <- parseVarLength(len)
        v <- valueParser(length)
      } yield cons(v)

    def parseA(length: Int): Parser[List[Byte]] =
      parseEMVDataElement(length, "^[a-z]*|[A-Z]*$".r, "Alphabetic")

    def parseB(length: Int): Parser[List[Byte]] =
      repN(length, parseSingleByte)

    def parseAN(length: Int): Parser[List[Byte]] =
      parseEMVDataElement(length, "^[a-z]*|[A-Z]*|[0-9]*$".r, "Alphabetic Numeric")

    def parseANS(length: Int): Parser[List[Byte]] = repN(length, parseSingleByte)

    def parseN(length: Int): Parser[List[Byte]] =
      parseEMVDataElement(length, "^[0-9]*$".r, "Numeric")

    def parseN(min: Int, max: Int)(length: Int): Parser[List[Byte]] =
      parseEMVDataElement(length, s"^0?[0-9]{${min},${max}}$$".r, "Numeric")

    def parseCn(length: Int): Parser[List[Byte]] =
      parseEMVDataElement(length, "^[0-9]+[F]*$".r, "Compressed numeric")

    def parseEMVDataElement(length: Int, pattern: Regex, emvType: String): Parser[List[Byte]] =
      repN(length, parseSingleByte).withFilter({ ba: Seq[Byte] =>
        val s: String = HexUtils.toHex(ba)
        pattern.findFirstIn(s).isDefined
      }).withFailureMessage(s"value is not ${emvType}")

    def parseAccountType: Parser[AccountType] =
      parseLeafwithTag(AccountType.tag, AccountType.length, parseN, AccountType(_))

    def parseAcquirerIdentifier: Parser[AcquirerIdentifier] =
      parseLeafwithTag(AcquirerIdentifier.tag, AcquirerIdentifier.length,
        parseN(AcquirerIdentifier.min, AcquirerIdentifier.max), AcquirerIdentifier(_))

    def parseAddtionalTerminalCapabilities: Parser[AdditionalTerminalCapabilities] =
      parseLeafwithTag(AdditionalTerminalCapabilities.tag, AdditionalTerminalCapabilities.length,
        parseB, AdditionalTerminalCapabilities(_))

    def parseAmountAuthorized: Parser[AmountAuthorized] =
      parseLeafwithTag(AmountAuthorized.tag, AmountAuthorized.length, parseN, AmountAuthorized(_))

    def parseAmountOtherBinary: Parser[AmountOtherBinary] =
      parseLeafwithTag(AmountOtherBinary.tag, AmountOtherBinary.length, parseB, AmountOtherBinary(_))

    def parseAmountOther: Parser[AmountOther] =
      parseLeafwithTag(AmountOther.tag, AmountOther.length, parseN, AmountOther(_))

    def parseAmountReferenceCurrency: Parser[AmountReferenceCurrency] =
      parseLeafwithTag(AmountReferenceCurrency.tag, AmountReferenceCurrency.length,
        parseB, AmountReferenceCurrency(_))

    def parseApplicationCryptogram: Parser[ApplicationCryptogram] =
      parseLeafwithTag(ApplicationCryptogram.tag, ApplicationCryptogram.length,
        parseB, ApplicationCryptogram(_))

    def parseApplicationFileLocator: Parser[ApplicationFileLocator] = {
      for {
        t <- parseLeafTag(ApplicationFileLocator.tag)
        length <- parseALength
        v <- parseEntries(length)
      } yield (ApplicationFileLocator(v))
    }

    def parseEntries(l: Int): Parser[List[AFLEntry]] =
      parseB(l).map(x => {
        val l: List[Seq[Byte]] = x.grouped(4).toList
        l.map(y => AFLEntry(y(0), y(1), y(2), y(3)))
      })

    def parseEMVTLV: Parser[BerTLV] = parseAccountType | parseAcquirerIdentifier |
      parseAddtionalTerminalCapabilities | parseAmountAuthorized | parseAmountOtherBinary |
      parseAmountOther | parseAmountReferenceCurrency | parseApplicationCryptogram |
      parseApplicationFileLocator

    def parseEMVTLV(in: String): ParseResult[BerTLV] = parse(parseEMVTLV, in)

  }

  trait SingleTagParser[T <: BerTLV] extends EMVParsers {

    val tag: BerTag

    val length: Int

    def factory(v: Seq[Byte]): T

    def parseValue(i: Int): Parser[Seq[Byte]]

    def parse(in: String): ParseResult[T] = parse(parse1, in)

    def parse1: Parser[T] =
      parseLeafwithTag[T](tag, length, parseValue, factory)

  }

}
