package pl.kandrzejczak.maalie

import pl.kandrzejczak.maalie.repository.{AlieItemInMemoryRepository, ItemEntryService, ItemEntryServiceImpl}
import spray.httpx.SprayJsonSupport._
import AlieItemJsonImplicits._
import spray.routing._
import spray.routing.directives.ExecutionDirectives._
import spray.routing.directives.MarshallingDirectives.as
import spray.routing.directives.MethodDirectives._
import spray.routing.directives.MarshallingDirectives._
import spray.routing.directives.PathDirectives._
import spray.routing.directives.RouteDirectives._
import spray.routing.RouteConcatenation._

import scala.concurrent.ExecutionContext.Implicits

class AlieItemsRoutes {

  private implicit val detachEc = Implicits.global
  //todo hardcoded
  val itemEntryService: ItemEntryService = new ItemEntryServiceImpl(new AlieItemInMemoryRepository)

  def routes: Route = {
    pathPrefix("items") {
      pathEnd{
        post {
          detach() {
            entity(as[AlieItem]){ item =>
              complete {
                itemEntryService.addItem(item)
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
        itemEntryService.getItemEntries
      }
    }
  }
}