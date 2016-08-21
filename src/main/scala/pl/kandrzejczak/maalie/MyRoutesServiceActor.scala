package pl.kandrzejczak.maalie

import akka.actor.Actor

class MyRoutesServiceActor extends Actor with MyRoutesService {
  def actorRefFactory = context

  def receive = runRoute(route)
}
