package org.iso7816

import java.nio.charset.Charset

import scodec.bits.ByteVector

/**
  * Created by Lau on 4/25/2016.
  */
case class AID(val value: ByteVector) {

  require(value != null)
  require(value.length >= 5 && value.length <= 16)

  val rid: ByteVector = value.take(5)

  val pix: ByteVector = value.drop(5)

  val category = value(0) match {
    case x if ((value(0) & 0xA0) == 0xA0) => AIDCategory.INTERNATIONAL
    case x if ((value(0) & 0xD0) == 0xD0) => AIDCategory.NATIONAL
    case x if ((value(0) & 0xE0) == 0xE0) => AIDCategory.STANDARD
    case _ => AIDCategory.PROPRIETARY
  }

  override def toString = value.toHex

}

object AID {

  val PPSE: AID = new AID(ByteVector("2PAY.SYS.DDF01".getBytes(Charset.forName("ASCII"))))

}

object AIDCategory extends Enumeration {
  val INTERNATIONAL, NATIONAL, STANDARD, PROPRIETARY, RESERVED = Value
}

