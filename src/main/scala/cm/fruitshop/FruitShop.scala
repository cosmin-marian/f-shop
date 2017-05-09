package cm.fruitshop

object FruitShop {
  type Item = String
  type Price = BigDecimal

  def computeListPrice(prices: Map[Item, Price], items: List[Item]): Price = {
    items.map(prices).sum
  }

  def computeDeductions(offers: List[Offer], prices: Map[Item, Price], items: List[Item]): Price = {
    offers.map(_.apply(prices, items)).sum
  }

  def computeWithOffers(offers: List[Offer], prices: Map[Item, Price], items: List[Item]) = {
    computeListPrice(prices, items) - computeDeductions(offers, prices, items)
  }
}

