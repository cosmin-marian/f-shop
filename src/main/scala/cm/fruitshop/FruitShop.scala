package cm.fruitshop

object FruitShop {
  type Item = String
  def computeListPrice(prices: Map[Item, Double], items: List[Item]) = {
    items.map(prices).sum
  }
}

object FruitShopExample {

  def main(args: Array[String]): Unit = {
    val prices: Map[FruitShop.Item, Double] = Map(
      "orange" → 0.25,
      "apple" → 0.6
    )

    val items = List("apple", "apple", "orange", "apple")

    println(s"Total price for $items is ${FruitShop.computeListPrice(prices, items)}")

  }
}


