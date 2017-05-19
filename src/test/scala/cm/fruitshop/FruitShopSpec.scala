package cm.fruitshop

import cm.fruitshop.FruitShop.{Item, Price}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class FruitShopSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks {

  val orangePrice: Price = 0.25
  val applePrice: Price = 0.6
  val bananaPrice: Price = BigDecimal("0.20")

  val prices: Map[Item, Price] = Map(
    "orange" → orangePrice,
    "apple" → applePrice,
    "banana" → bananaPrice
  )

  val simpleOffers = List(
    new OfferMForPriceOfN("orange", 3, 2),
    new OfferMForPriceOfN("apple", 2, 1)
  )

  val mixedOffers = List(
    new OfferMForPriceOfN("orange", 3, 2),
    new Offer2ForPriceOf1Mixed("apple", "banana")
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

  val examplesWithOffer = Table[List[Item], Price](
    ("Items", "Total Price With Offers"),
    List() → 0.0,
    List("apple") → applePrice,
    List("orange") → orangePrice,
    List("apple", "orange") → (applePrice + orangePrice),
    List("apple", "apple", "orange", "apple") → (2 * applePrice + orangePrice),
    List("orange", "orange", "orange", "apple") → (applePrice + 2 * orangePrice)
  )

  it should "compute total price with offers" in {
    forAll(examplesWithOffer) { case (items, totalPrice) ⇒
      FruitShop.computePriceWithOffers(simpleOffers, prices, items) shouldEqual totalPrice
    }
  }

  val exampleStep3 = Table[List[Item], Price](
    ("Items", "Total Price With Offers"),
    List() → 0.0,
    List("apple") → applePrice,
    List("orange") → orangePrice,
    List("apple", "apple") -> applePrice,
    List("apple", "banana") -> applePrice,
    List("apple", "apple", "banana", "banana") -> (2 * applePrice),
    List("apple", "banana", "banana", "banana") -> (applePrice + bananaPrice)
  )

  it should "compute the total price with offers" in {
    forAll(exampleStep3) {
      case (items, totalPrice) ⇒
        FruitShop.computePriceWithOffers(mixedOffers, prices, items) shouldEqual totalPrice
    }
  }
}