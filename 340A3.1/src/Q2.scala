// CMPT 340
// ASSIG 3.1
// KODY MANSTYRSKI
// KOM607
// 11223681

object Q2 {

  trait Partial[+A,+B] {

    case class Errors[+A](get: Seq[A]) extends Partial[A, Nothing]

    case class Success[+B](get: B) extends Partial[Nothing, B]

    def map[X](f: B => X): Partial[A, X]  = this match{
      case Success(b) => Success(f(b))
      case Errors(a) => Errors(a)
    }

    def flatMap[AA >: A, X](f: B => Partial[AA, X]): Partial[AA, X] = this match{
      case Success(a) => f(a)
      case Errors(b) => Errors(b)
    }

    def getOrElse[C >: B]( default: => C): C = this match{
      case Success(b) => b
      case Errors(_) => default
    }

    def orElse[AA >: A, BB >: B](f: => Partial[AA, BB]): Partial[AA, BB] = this match {
      case Success(a) => Success(a)
      case Errors(b) => f
    }

    def map2[AA >: A, C, D](b: Partial[AA, C])(f: (B, C) => D): Partial[AA, D] = {
      for{
        a <- this;
        c <- b
      } yield f(a, c)
    }

    /*def traverse[C](f: B => Partial[A, C]): Partial[A, List[C]] = {}

    def Try(f: =>B): Partial[A, B] = {
      try Success(f)
      catch {
        case e: Exception => Seq(e, this.Errors)
      }
    }
*/
  }

}
