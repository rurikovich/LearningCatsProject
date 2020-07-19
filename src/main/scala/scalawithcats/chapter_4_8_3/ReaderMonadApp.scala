package scalawithcats.chapter_4_8_3

import cats.data.Reader
import cats.syntax.applicative._ // for pure

object ReaderMonadApp extends App {

  final case class Db(usernames: Map[Int, String],
                      passwords: Map[String, String])


  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = {
    new DbReader(db => db.usernames.get(userId))
  }

  def checkPassword(username: String, password: String): DbReader[Boolean] = {
    new DbReader((db: Db) => db.passwords.toSeq.contains((username, password)))
  }

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =

    for {
      name <- findUsername(userId)
      isPswdOk <-name.map{
        name=>checkPassword(name,password)
      }.getOrElse(false.pure[DbReader])

    } yield isPswdOk




  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

 println( checkLogin(1, "zerocool").run(db))
  // res7: cats.package.Id[Boolean] = true
  println(checkLogin(4, "davinci").run(db))
  // res8: cats.package.Id[Boolean] = false

}

