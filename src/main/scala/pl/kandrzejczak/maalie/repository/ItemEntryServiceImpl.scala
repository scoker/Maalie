package pl.kandrzejczak.maalie.repository

import pl.kandrzejczak.maalie.AlieItem

class ItemEntryServiceImpl(repository: AlieItemRepository) extends ItemEntryService {

  override def getItemEntries: List[AlieItem] = List(AlieItem("id-1", "Item-1", "Description-1", "1.0"), AlieItem("id-2", "Item-2", "Description-2", "2.0"))

  override def addItem(item: AlieItem): AlieItem = repository.add(item)
}