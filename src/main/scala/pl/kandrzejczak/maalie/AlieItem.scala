package pl.kandrzejczak.maalie

import spray.json.DefaultJsonProtocol

case class AlieItem(id: String, name: String, description: String, buyPrice: String)

object AlieItemJsonImplicits extends DefaultJsonProtocol {
  implicit val alieItemFormat = jsonFormat4(AlieItem)
}

//additionalCosts: List[AdditionalCost],
//doOrderAgain: Boolean,
//link: String,
//dateOfOrder: DateTime,
//dateOfArrival: DateTime,
//photos: List[Photo],
//tags: List[String]