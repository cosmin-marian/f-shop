package cm.fruitshop

object FruitShop {
  type Item = String
  def computeListPrice(prices: Map[Item, Double], items: List[Item]) = {
    items.map(prices).sum
  }
}

