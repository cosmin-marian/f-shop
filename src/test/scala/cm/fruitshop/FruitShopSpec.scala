package cm.fruitshop

import cm.fruitshop.FruitShop.{Item, Price}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class FruitShopSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  val orangePrice = 0.25
  val applePrice = 0.6

  val prices: Map[Item, Price] = Map(
    "orange" → orangePrice,
    "apple" → applePrice
  )

  val examples = Table[List[Item], Price](
    ("Items", "Total Price"),
    List() → 0.0,
    List("apple") → applePrice,
    List("orange") → orangePrice,
    List("apple", "orange") → (applePrice + orangePrice),
    List("apple", "apple", "orange", "apple") → (3 * applePrice + orangePrice)
  )

  "Fruit shop" should "compute the total price" in {
    forAll(examples) { case (items, totalPrice) ⇒
      FruitShop.computeListPrice(prices, items) shouldEqual totalPrice

    }
  }

}
