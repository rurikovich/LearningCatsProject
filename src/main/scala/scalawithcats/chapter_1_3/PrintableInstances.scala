package scalawithcats.chapter_1_3

object PrintableInstances {
  implicit val printableString: Printable[String] = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val printableInt: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString
  }

}