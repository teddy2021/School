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

    def flatMap[AA >: A, X](f: B => Partial[AA, X]): Partial[AA, X] = this match {
      case Success(a) => f(a)
      case Errors(b) => Errors(b)
    }

    def getOrElse[C >: B](default: => C): C = this match {
      case Success(a) => a
      case Errors(b) => default
    }

    def orElse[AA >: A, BB >: B](f: => Partial[AA, BB]): Partial[AA, BB] = this match {
      case Success(a) => Success(a)
      case Errors(b) => f
    }

    def map2[AA >: A, C, D](b: Partial[AA, C])(f: (B, C) => D): Partial[AA, D] = {
      for {
        a <- this;
        c <- b
      } yield f(a, c)
    }

    def traverse[A, B, C](itm: List[B])(f: B => Partial[A, C]): Partial[A, List[C]] =
      itm.foldRight[Partial[A, List[C]]](Success(Nil))((a, b) => f(a).map2(b)(_ :: _))

    /*
    def Try[B](f: =>B): Partial[A, B] = {
      try Success(f)
      catch {
        case e: Exception => Errors()
      }
    }
  }
*/
  }

  case class Errors[+A](get: Seq[A]) extends Partial[A, Nothing]

  case class Success[+B](get: B) extends Partial[Nothing, B]

    def genList(x: Int, default: List[Int]): List[Int] = {
      if(x > 0) genList(x -1, (1+ Random.nextInt(100) - Random.nextInt(110))::default)
      else (default)
    }

    def Loop(n: Int): Unit = {
      if(n > 0) {
        println("Iteration: " + n)
        val consider = genList(5 + Random.nextInt(20), Nil)

        Loop(n - 1)
      }
    }

    def main(args: Array[String]): Unit = {
      Loop(5)
  }
}