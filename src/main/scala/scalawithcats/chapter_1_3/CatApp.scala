package scalawithcats.chapter_1_3

object CatApp extends App {
  val cat = Cat("ss", 3, "ffff")
  Printable.print(cat)

}


case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}


