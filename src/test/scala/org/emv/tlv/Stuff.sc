import javax.smartcardio.TerminalFactory

import org.emv.tlv._
import org.emv.tlv.EMVTLV.EMVParser
import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag}
import org.tlv.TLV._
import org.tlv.HexUtils._
import org.emv.{PCSCCard, GeneralParameters}
import com.softwaremill.quicklens._
import org.iso7816.AID

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
} yield (a + b)


t1.unsafePerformSyncAttempt match {
  case \/-(x) => println(x)
  case _ =>
}


PCSCCard.getReaders.unsafePerformSyncAttempt match {
  case \/-(x) => x.map(l => println(l))
  case e => println(e)
}

val params = new GeneralParameters(Nil)


val p = params.modify(_.tlv).using(_ :+ ApplicationInterchangeProfile("0000")).
  modify(_.tlv).using(_ :+ ApplicationIdentifier(AID("A0000000041010")))

p.tlv.length

val p2 = p.modify(_.tlv.each.when[ApplicationInterchangeProfile]).
  using(_.withIssuerAuthenitcationSupportedSet)

p2.tlv.head match {
  case a: ApplicationInterchangeProfile => println("found it " + a.isIssuerAuthenitcationSupported)
  case l@_ => println(s"Didnt find it ${l}")
}

trait Animal
case class Dog(age: Int) extends Animal
case class Cat(ages: List[Int]) extends Animal

case class Zoo(animals: List[Animal])

val zoo = Zoo(List(Dog(4), Cat(List(3, 12, 13))))

val olderZoo = zoo.modifyAll(
  _.animals.each.when[Dog].age,
  _.animals.each.when[Cat].ages.at(0)
).using(_ + 1)