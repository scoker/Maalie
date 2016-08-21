package pl.kandrzejczak.maalie

import org.scalatest.{BeforeAndAfter, FlatSpecLike}
import org.scalatest.Matchers._
import org.specs2.mock.Mockito
import spray.http.{ContentTypes, HttpEntity, StatusCodes}
import spray.testkit.ScalatestRouteTest
import spray.httpx.SprayJsonSupport._

class AlieItemsRoutesTest extends FlatSpecLike with ScalatestRouteTest with BeforeAndAfter with Mockito {
  private var tested: AlieItemsRoutes = _

  before {
    tested = new AlieItemsRoutes()
  }

  "items get" should "return a 200 status when queried" in {
    Get("/items") ~> tested.routes ~> check {
      status shouldEqual StatusCodes.OK
    }
  }

  it should "return all items when called" in {
    val expected = List(AlieItem("id-1", "Item-1", "Description-1", "1.0"), AlieItem("id-2", "Item-2", "Description-2", "2.0"))
    Get("/items") ~> tested.routes ~> check {
      import AlieItemJsonImplicits._
      responseAs[List[AlieItem]] shouldEqual expected
    }
  }

  "items post" should "add item to system" in {
    val expected = AlieItem("id-1", "Item-1", "Description-1", "1.0")
    Post("/items", HttpEntity(ContentTypes.`application/json`, json)) ~> tested.routes ~> check {
      import AlieItemJsonImplicits._
      status shouldEqual StatusCodes.OK
      responseAs[AlieItem] shouldEqual expected
    }
  }

  private val json =
    """{
      |  "id": "id-1",
      |  "name": "Item-1",
      |  "description": "Description-1",
      |  "buyPrice": "1.0"
      |  }""".stripMargin
}