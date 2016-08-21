package pl.kandrzejczak.maalie

import akka.actor.Actor
import com.google.inject.Inject
import spray.routing.HttpService

class RoutingActor extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(alieItemsRoutes.routes)

  @Inject private var alieItemsRoutes: AlieItemsRoutes = _
}
