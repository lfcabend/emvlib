import com.typesafe.scalalogging.LazyLogging
import org.emv.LibNFCCard
import scodec.bits._
import scalaz.{-\/, \/-}

/**
  * Created by lau on 12/6/16.
  */
object TestMain extends App with LazyLogging {

//  logger.debug("starting app")
//
//
//  val p = for {
//    cntx <- LibNFCCard.initialize
//    cntx <- LibNFCCard.waitForCardOnTerminal(cntx)
//    r <- LibNFCCard.transmit(cntx, hex"00A404000E325041592E5359532E444446303100")
//  } yield(r)
//
//
//  p.unsafePerformSyncAttempt match {
//    case \/-(x) => println(x)
//    case -\/(e)=> println(s"did not work: ${e.getMessage}"); e.printStackTrace()
//  }

}
