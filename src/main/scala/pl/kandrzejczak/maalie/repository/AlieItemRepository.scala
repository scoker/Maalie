package pl.kandrzejczak.maalie.repository

import pl.kandrzejczak.maalie.AlieItem

trait AlieItemRepository {
  def get(id: String): Option[AlieItem]
  def getAll: List[AlieItem]
  def add(item: AlieItem): AlieItem
  def update(updatedItem: AlieItem): AlieItem
}

object AlieItemRepository {
  case class NoItemsInRepository(message: String = "There are no items in repository") extends Exception(message)
  case class NoItemWithGivenIdInRepository(message: String = "There is no item with given id") extends Exception(message)
}
