package scalawithcats.chapter_4_7_3

import cats.Id
import cats.data.{Writer, WriterT}
import cats.instances.vector._
import cats.syntax.applicative._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent._
import scala.concurrent.duration._ // for pure

object WriterMonadApp extends App {

  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A) = try body finally Thread.sleep(100)

  def factorial(n: Int): Logged[Int] = {

    val ans: WriterT[Id, Vector[String], Int] = slowly(
      if (n == 0)
        1.pure[Logged]
      else {
        for {
          a <- n.pure[Logged]
          b <- factorial(n - 1)
        } yield a * b
      }
    )


    ans.mapWritten(_ ++ Vector(s" fact $n ${ans.value} "))
  }


  Await.result(Future.sequence(Vector(
    Future(factorial(5)).map(w=>println(w.written.mkString("->"))),
    Future(factorial(5)).map(w=>println(w.written.mkString("->")))
  )), 5.seconds)

}
