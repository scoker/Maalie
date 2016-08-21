package pl.kandrzejczak.maalie

import spray.routing._
import spray.httpx.SprayJsonSupport._
import AlieItemJsonImplicits._
import pl.kandrzejczak.maalie.repository.{AlieItemInMemoryRepository, ItemEntryService, ItemEntryServiceImpl}

trait MyRoutesService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher
  //todo hardcoded
  val itemEntryService: ItemEntryService = new ItemEntryServiceImpl(new AlieItemInMemoryRepository)

  val route = {
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