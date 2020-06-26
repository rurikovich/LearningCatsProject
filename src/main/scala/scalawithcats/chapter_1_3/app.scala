package scalawithcats.chapter_1_3

object app extends App {

  case class Cat(name: String, age: Int, color: String)

  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }

  val cat = Cat("ss", 3, "ffff")

  Printable.print(cat)


}
