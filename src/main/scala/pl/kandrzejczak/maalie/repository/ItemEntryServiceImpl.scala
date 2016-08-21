package pl.kandrzejczak.maalie.repository

import com.google.inject.Inject
import pl.kandrzejczak.maalie.AlieItem

class ItemEntryServiceImpl @Inject()(repository: AlieItemRepository) extends ItemEntryService {

  override def getItemEntries: List[AlieItem] = repository.getAll

  override def addItem(item: AlieItem): AlieItem = repository.add(item)
}