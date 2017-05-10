package cm.fruitshop

import cm.fruitshop.FruitShop.{Item, Price}

/**
  * Simple implementation of an Offer that, given a list of items computes a deduction
  * that needs to be applied.
  * This solution assumes that the offers can be applied independently, i.e. the order in each
  * they are applied is not important
  */
trait Offer {
  def apply(prices: Map[Item, Price], items: List[Item]): Price
}

class OfferMForPriceOfN(item: Item, nItems: Int, forPriceOf: Int) extends Offer {

  override def apply(prices: Map[Item, Price], items: List[Item]): Price = {
    val boughtItems = items count (_ == item)
    val groups = boughtItems / nItems
    val itemsToPay = groups * forPriceOf + boughtItems % nItems

    val deduction = (boughtItems - itemsToPay) * prices(item)
    deduction
  }
}
