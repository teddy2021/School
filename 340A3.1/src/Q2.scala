import scala.annotation.tailrec
import scala.util.Random
// CMPT 340
// ASSIG 3.1
// KODY MANSTYRSKI
// KOM607
// 11223681

object Q2 {

  trait Partial[+A, +B]{

    def map[C](f: B => C): Partial[A, C] = this match {
      case Success(b) => Success(f(b))
      case Errors(a) => Errors(a)
    }

    def flatMap[AA >: A, BB >: B](f: B => Partial[AA, BB]): Partial[AA, BB] = this match {
      case Success(b) => f(b)
      case Errors(a) => Errors(a)
    }

    def getOrElse[BB >: B](itm: BB):BB = this match{
      case Success(b) => b
      case Errors(_) => itm
    }

    def orElse[AA >: A, BB >:B](itm: Partial[AA, BB])(f: => Partial[AA, BB]): Partial[AA, BB] = itm match {
      case Success(b) => Success(b)
      case Errors(_) => f
    }

    def map2[AA >: A, C, D](c1: Partial[AA, C])(f: (B, C) => D): Partial[AA, Int] =
      for {
        b <- this
        c <- c1// claims c1 is of type Q2.Partial[AA, D]
      } yield f(b, c)

    def traverse[A, B, C](lst: List[C])(f: C => Partial[A, B]): Partial[A, List[B]] = lst match {
      case Nil => Errors(Nil)
      case h::t => (f(h) map2 traverse(t)(f))(_ :: _) // Claims is of type Q2.Partial[A, Int]
    }

  }

  case class Errors[+A](get: Seq[A]) extends Partial[A, Nothing]

  case class Success[+B](get: B) extends Partial[Nothing, B]

  def genList[A](x: Int, default: List[Int]): List[Int] = {
    if(x > 0) genList(x -1, (1+ Random.nextInt(100) - Random.nextInt(110))::default)
    else default
  }

  def Loop(n: Int): Unit = {
    if(n > 0) {
      println("Iteration: " + (6-n))
      val consider = Success(genList(5 + Random.nextInt(20), Nil))
      println("List: " + consider)
      Loop(n - 1)
    }
  }

  def main(args: Array[String]): Unit = {
    Loop(5)
  }
}