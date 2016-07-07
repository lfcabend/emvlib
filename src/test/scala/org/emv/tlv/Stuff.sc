import javax.smartcardio.TerminalFactory

import org.emv.tlv.{AccountType, ApplicationPreferredName, ApplicationPriorityIndicator, EMVTLV}
import org.emv.tlv.EMVTLV.EMVParser
import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag}
import org.tlv.HexUtils._
import org.emv.Card

import scalaz._
import scalaz.concurrent.Task
val a = HexUtils.hex2Bytes("A0")(0)
//HexUtils.toHex(List(a))
val b = 0xA0.toByte
a == 0xA0.toByte
val f: Seq[Int] = Array(1, 2, 3)
val y = f match {
  case x :: xs => "list"
  case _ => "no list"
}
val h: Seq[Byte] = "00".fromHex
h.toHex


val t1 = Task {
  8
}

val t2 = Task {
  6
}

for {
  a <- t1
  b <- t2
} yield ( a + b)


t1.unsafePerformSyncAttempt match {
  case \/-(x) => println(x)
  case _ =>   
}


Card.getReaders.unsafePerformSyncAttempt match {
  case \/-(x) => x.map(l => println(l))
  case e => println(e)
}


TerminalFactory.getDefault.terminals.list