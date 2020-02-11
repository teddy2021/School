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

  /*def repHalve[A](l: List[A]): List[List[A]] = {
    val sz = l.size / 2
    //List(unfold((z: List[A])=>z.size <= sz, )(l), repHalve())
  }*/

  def main(args: Array[String]): Unit ={
    println("The bin of 25 is " + int2bin(25))
  }

}
