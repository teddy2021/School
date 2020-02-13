// CMPT 340
// Assignment 2
// Kody Manastyrski
// Kom607
// 11223681

object Problem3 {

  def unfold[A, B](p: A=>Boolean, h: A=>B, t: A=>A)(x: A): List[B] = {
    if(p(x)) Nil
    else h(x)::unfold(p, h, t)(t(x))
  }

  def int2bin(x: Int): List[Int] ={
    unfold((z: Int) => z == 0, (z: Int) => z%2, (z: Int) => z/2)(x).reverse
  }

  def repHalve[A](l: List[A]): List[List[A]] = {
    val sz = l.size / 2
    unfold((lst: List[A])=> lst == Nil, (lst: List[A]) => lst.slice(0, (lst.size / 2) + 1), (lst: List[A]) => lst.slice((lst.size / 2) + 1, lst.size))(l)
  }

  def main(args: Array[String]): Unit ={
    println("The bin of 25 is " + int2bin(25))
    val lst = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17)
    println("lst: " + lst + " with size " + lst.size, " and halfway point " + lst.size/2, " with value " + lst(lst.size/2) )
    println("The repHalve of " + lst + " is " + repHalve(lst))
  }

}
