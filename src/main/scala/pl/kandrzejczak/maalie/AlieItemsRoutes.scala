package pl.kandrzejczak.maalie

import pl.kandrzejczak.maalie.repository.ItemEntryService
import spray.httpx.SprayJsonSupport._
import AlieItemJsonImplicits._
import com.google.inject.Inject
import spray.routing._
import spray.routing.directives.ExecutionDirectives._
import spray.routing.directives.MarshallingDirectives.as
import spray.routing.directives.MethodDirectives._
import spray.routing.directives.MarshallingDirectives._
import spray.routing.directives.PathDirectives._
import spray.routing.directives.RouteDirectives._
import spray.routing.RouteConcatenation._

import scala.concurrent.ExecutionContext.Implicits

class AlieItemsRoutes @Inject() (itemService: ItemEntryService) {

  private implicit val detachEc = Implicits.global

  def routes: Route = {
    pathPrefix("items") {
      pathEnd{
        post {
          detach() {
            entity(as[AlieItem]){ item =>
              complete {
                itemService.addItem(item)
            }
           }
          }
        } ~ getItems()
      }
    }
  }

  private def getItems(): Route = {
    get {
      complete {
        itemService.getItemEntries
      }
    }
  }
}