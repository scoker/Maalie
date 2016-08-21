package pl.kandrzejczak.maalie.repository
import pl.kandrzejczak.maalie.AlieItem
import pl.kandrzejczak.maalie.repository.AlieItemRepository.{NoItemWithGivenIdInRepository, NoItemsInRepository}

class AlieItemInMemoryRepository extends AlieItemRepository {

  private var items: Map[String, AlieItem] = Map()

  override def get(id: String): Option[AlieItem] = items.get(id)

  override def getAll: List[AlieItem] = items.values.toList

  override def add(item: AlieItem): AlieItem = {
    items += item.id -> item
    item
  }

  override def update(updatedItem: AlieItem): AlieItem = {
    if(items.size <= 0) {
      throw new NoItemsInRepository()
    }

    items.get(updatedItem.id) match {
      case Some(item) => items += updatedItem.id -> updatedItem
      case None => throw new NoItemWithGivenIdInRepository()
    }
    updatedItem
  }
}
