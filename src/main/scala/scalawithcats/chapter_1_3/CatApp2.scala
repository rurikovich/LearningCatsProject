package scalawithcats.chapter_1_3

object CatApp2 extends App {

  import PrintableSyntax._

  Cat("aaa", 4, "sds").print
}

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = printable.format(value)

    def print(implicit printable: Printable[A]): Unit = println(printable.format(value))

  }

}