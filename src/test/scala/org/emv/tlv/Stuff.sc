import org.emv.tlv.{ApplicationPriorityIndicator, ApplicationPreferredName, AccountType, EMVTLV}
import org.emv.tlv.EMVTLV.EMVParser
import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag}
import org.tlv.HexUtils._
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

val tag: BerTag = "61"
tag.isConstructed
val input2 = HexUtils.hex2Bytes("9F0702FFFF")
trait Spec[A, V] {
  def a: A
  def v: V
}
trait SpecC extends Spec[Int, Int]
//trait Par[L[A, V <: Int] <: Spec[A, V]] {
//
//  def bgagaga: A
//
//}


//case class ParC(i:Int) extends SpecC {
//
//  override def a: Int = i
//
//  override def v: Int = i
//
//}

//trait L[U] {
//
//  def u: U
//
//}
//
//trait LL extends L[Int] {
//
//  def u = 8
//
//}
//
//
//trait LLL extends L[String] {
//
//  def u = "Strei"
//
//}
//
//case class PFF(val v: String) extends LL with LLL
//
//println(PFF("dsada"))
trait T  {

  abstract class C


  def c: C
}
trait T1 extends T {


}



