package pl.kandrzejczak.maalie.repository

import pl.kandrzejczak.maalie.AlieItem

trait ItemEntryService {
  def addItem(item: AlieItem): AlieItem

  def getItemEntries: List[AlieItem]
}
