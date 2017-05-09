package cm.fruitshop

object FruitShop {
  type Item = String
  type Price = BigDecimal

  def computeListPrice(prices: Map[Item, Price], items: List[Item]) = {
    items.map(prices).sum
  }
}

