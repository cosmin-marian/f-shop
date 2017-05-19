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

class Offer2ForPriceOf1Mixed(item1: Item, item2: Item) extends Offer {
  override def apply(prices: Map[Item, Price], items: List[Item]): Price = {
    val (largerPriceItem, smallerPriceItem) = if (prices(item1) > prices(item2)) (item1, item2) else (item2, item1)
    val nLargerPrice = items count (_ == largerPriceItem)
    val nSmallerPrice = items count (_ == smallerPriceItem)

    val mixedGroups = math.min(nLargerPrice, nSmallerPrice)

    def priceForLeftItems(nItems: Int, price: Price): Price = {
      val leftItems = nItems - mixedGroups
      if (leftItems > 0) price * (leftItems / 2 + leftItems % 2)
      else 0
    }

    val priceToPay =
      mixedGroups * prices(largerPriceItem) +
        priceForLeftItems(nLargerPrice, prices(largerPriceItem)) +
        priceForLeftItems(nSmallerPrice, prices(smallerPriceItem))

    val initialPrice = nLargerPrice * prices(largerPriceItem) + nSmallerPrice * prices(smallerPriceItem)
    val deduction = initialPrice - priceToPay
    deduction
  }

}
