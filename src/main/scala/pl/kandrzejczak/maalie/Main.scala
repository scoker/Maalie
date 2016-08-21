package pl.kandrzejczak.maalie

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._


object Main extends App {
  implicit val system = ActorSystem("Maalie")
  val service = system.actorOf(Props[MyRoutesServiceActor], "sj-rest-service")
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}



