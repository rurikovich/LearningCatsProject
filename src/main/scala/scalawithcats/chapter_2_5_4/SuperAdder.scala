package scalawithcats.chapter_2_5_4

import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.instances.option._ // for Monoid
import cats.syntax.semigroup._ // for |+|

object SuperAdder extends App {

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)

    override def empty: Order = Order(0, 0)
  }

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(monoid.empty)(_ |+| _)

  val list = List(Some(1), Some(2), Some(3), Some(4), None)
  println(add(list))

  val orderList = List(Order(1, 1), Order(2, 2), Order(3, 3))
  println(add(orderList))
}

case class Order(totalCost: Double, quantity: Double)
