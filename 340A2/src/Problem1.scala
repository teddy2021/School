// CMPT 340
// Assignment 2
// Kody Manastyrski
// Kom607
// 11223681

object Problem1 {
  def Shuffle[A](l1: List[A], l2: List[A]): List[A] = {
    if(l1.size == 0) l2
    else if(l2.size == 0) l1
    else
      l1 match {
       case Nil => List()
       case card::deck => card::Shuffle(l2, deck)
      }
  }

  def Split[A](l: List[A], n: Int):List[List[A]] = {
    def firsthalf(lst: List[A], n: Int): List[A] = {
      lst match {
        case Nil => Nil
        case card::deck if n > 0 => (card :: firsthalf(deck, n - 1))
        case _::_ if n <= 0 => Nil
      }
    }
    def secondhalf(lst: List[A], n: Int): List[A] = {
      lst match{
        case Nil => Nil
        case card::deck if n <= 0 => card :: secondhalf(deck, n)
        case _::deck if n > 0 => secondhalf(deck, n-1)
      }
    }
    List(firsthalf(l, n),secondhalf(l, n))
  }


  def outshuffle[A](l: List[A]):List[A]={
    val ls = Split(l, (l.size/2))
    Shuffle(ls(0), ls(1))
  }

  def inshuffle[A](l: List[A]): List[A]={
    val ls = Split(l, (l.size/2))
    Shuffle(ls(1), ls(0))
  }

  def nshuffle[A](l: List[A], n:Int)(f: List[A] => List[A]): List[A] = {
    if (n <= 0)  f(l)
    else {
      val lst: List[A] = f(l)
      nshuffle(lst, n - 1)(f)
    }
  }

  def howManyShuffles[A](l1: List[A], l2: List[A])(f: (List[A]) => List[A]): Int = {

    def run(n: Int, lst1: List[A])(fx: (List[A]) => List[A]): Int = {
      val l = fx(lst1)
      if (l == l2) n
      else run(n + 1, l)(fx)
    }

    run(0,l1)(f)
  }

  def generateDeck(n: Int): List[Int] = {
    if(n <= 0) Nil
    else n::generateDeck(n-1)
  }

  def reverse[A](l: List[A]): List[A] = l match{
    case Nil => Nil
    case card::deck => reverse(deck):::List(card)
  }

  def main(args: Array[String]): Unit ={
    val deck1 = generateDeck(200)
    println(howManyShuffles(outshuffle(deck1), deck1)(outshuffle _) + " shuffles to equality using outshuffle")
    println(howManyShuffles(inshuffle(deck1), deck1)(inshuffle _) + " shuffles to equality using inshuffle")
    println(howManyShuffles(deck1, reverse(deck1))(inshuffle(_)) + " shuffles to reverse using inshuffle")


  }

}
