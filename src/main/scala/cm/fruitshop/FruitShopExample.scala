package cm.fruitshop

import cm.fruitshop.FruitShop.{Item, Price}

object FruitShopExample {
  def main(args: Array[String]): Unit = {
    val prices: Map[Item, Price] = Map(
      "orange" → 0.25,
      "apple" → 0.6
    )

    val items = List("apple", "apple", "orange", "apple")

    println(s"Total price for $items is ${ FruitShop.computeListPrice(prices, items) }")

  }
}
