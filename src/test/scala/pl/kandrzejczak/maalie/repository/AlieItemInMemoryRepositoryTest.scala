package pl.kandrzejczak.maalie.repository

import org.scalatest.{BeforeAndAfter, FlatSpecLike, Matchers}
import pl.kandrzejczak.maalie.AlieItem
import pl.kandrzejczak.maalie.repository.AlieItemRepository.{NoItemWithGivenIdInRepository, NoItemsInRepository}

//todo to powinien byc generyczny test z podlaczanymi repositories jako zmienne
class AlieItemInMemoryRepositoryTest extends FlatSpecLike with BeforeAndAfter with Matchers {

  private var tested: AlieItemRepository = _

  before {
    tested = new AlieItemInMemoryRepository()
  }

  "getAll" should "return empty list when no items were added" in {
    tested.getAll shouldEqual List()
  }

  it should "return given item when added to repository" in {
    val expectedItem = anItem()
    tested.add(expectedItem)
    tested.getAll shouldEqual List(expectedItem)
  }

  it should "return three added items after adding three times same item" in {
    tested.add(anItem(id = "id-1"))
    tested.add(anItem(id = "id-2"))
    tested.add(anItem(id = "id-3"))
    tested.getAll should contain theSameElementsAs List(anItem(id = "id-1"), anItem(id = "id-2"), anItem(id = "id-3"))
  }

  "get" should "return item with given id when only this item exists in repository" in {
    val givenId = "id-1"
    val expectedItem = anItem(givenId)
    tested.add(expectedItem)
    tested.get(givenId) shouldEqual Some(expectedItem)
  }

  it should "return item with given id when multiple items are in repository" in {
    val givenId = "id-1"
    val expectedItem = anItem(givenId)
    tested.add(anItem())
    tested.add(expectedItem)
    tested.add(anItem())
    tested.get(givenId) shouldEqual Some(expectedItem)
  }

  it should "return None when there is no item with given-id" in {
    val givenId = "non-existent-id"
    tested.add(anItem())
    tested.get(givenId) shouldEqual None
  }

  "update" should "update an item with given id in repository" in {
    val givenId = "given-id"
    val toUpdateItem = anItem(id = givenId)
    val updatedItem = toUpdateItem.copy(name = "updated-name")
    tested.add(toUpdateItem)
    tested.update(updatedItem)
    tested.get(givenId) shouldEqual Some(updatedItem)
  }

  it should "not change item count in repository" in {
    val toUpdateItem = anItem()
    val updatedItem = toUpdateItem.copy(name = "updated-name")
    tested.add(anItem(id = "an Id 1"))
    tested.add(toUpdateItem)
    tested.update(updatedItem)
    tested.getAll.size shouldEqual 2
  }

  it should "throw an exception when trying to update item when no items were added" in {
    val toUpdateItem = anItem()
    an [NoItemsInRepository] should be thrownBy tested.update(toUpdateItem)
  }

  it should "throw an exception when trying to update an absent item" in {
    val givenId = "non-existent-id"
    val toUpdateItem = anItem(id = givenId)
    tested.add(anItem(id = "id-1"))
    tested.add(anItem(id = "id-2"))
    an [NoItemWithGivenIdInRepository] should be thrownBy tested.update(toUpdateItem)
  }

  private def anItem(id: String = "some-id",
                     name: String = "some-name",
                     desc: String = "some-description",
                     price: String = "some-price"): AlieItem = AlieItem(id, name, desc, price)

}
