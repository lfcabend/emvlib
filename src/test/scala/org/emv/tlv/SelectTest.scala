package org.emv.tlv

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.::

/**
  * Created by lau on 12/14/16.
  */
class SelectTest extends FlatSpec with Matchers {

//  "A FCI templet" should " be able to work with select" in {
//
//
//    """
//      |6F File Control Information (FCI) Template
//      | 	84 Dedicated File (DF) Name
//      | 	 	325041592E5359532E4444463031
//      | 	A5 File Control Information (FCI) Proprietary Template
//      | 	 	BF0C File Control Information (FCI) Issuer Discretionary Data
//      | 	 	 	61 Application Template
//      | 	 	 	 	4F Application Identifier (AID) – card
//      | 	 	 	 	 	A0000000031010
//      | 	 	 	 	50 Application Label
//      | 	 	 	 	 	V I S A D E B I T
//      | 	 	 	 	87 Application Priority Indicator
//      | 	 	 	 	 	01
//    """.stripMargin
//
//    val fciTemplate =
//      FileControlInformationTemplate(List(
//        DedicatedFileName(AID("A0000000041010")),
//        FileControlInformationProprietaryTemplate(List(
//          FileControlInformationIssuerDiscretionaryData(List(
//            ApplicationTemplate(List(
//              ApplicationDedicatedFileName(AID("A0000000041010"))
//            )),
//            ApplicationTemplate(List(
//              ApplicationDedicatedFileName(AID("A0000000042020"))
//            ))
//          ))
//        ))))
//
//
//    val templates1 = fciTemplate.select(PathEx("6F") :: PathEx("A5") :: PathEx("BF0C") :: PathEx("61") :: Nil)
//    val expectedResult = Some(List(FileControlInformationTemplate(List(
//      FileControlInformationProprietaryTemplate(List(
//        FileControlInformationIssuerDiscretionaryData(List(
//          ApplicationTemplate(List(
//            ApplicationDedicatedFileName(AID("A0000000041010"))
//          )),
//          ApplicationTemplate(List(
//            ApplicationDedicatedFileName(AID("A0000000042020"))
//          ))
//        ))
//      ))))))
//    assert(expectedResult == templates1)
//
//
//    val r2 = fciTemplate.selectLast(PathEx("6F") :: PathEx("A5") :: PathEx("BF0C") :: PathEx("61") :: PathEx("4F") :: Nil)
//    println(r2)
//    val expectedR2 = Some(List(
//      ApplicationDedicatedFileName(AID("A0000000041010")),
//      ApplicationDedicatedFileName(AID("A0000000042020"))
//    ))
//    assert(expectedR2 == r2)
//  }
}
