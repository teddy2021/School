// CMPT 340
// Assignment 2
// Kody Manastyrski
// Kom607
// 11223681

object Problem2 {

  sealed trait Tree[+A]
    case object Null extends Tree[Nothing]
    case class Branch[+A](Left: Tree[A], Right: Tree[A], value: A) extends Tree[A]

  object Tree {
    def inOrder[A](t: Tree[A]): List[A] = t match {
      case Null => Nil
      case Branch(l, r, v) => inOrder(l) ::: List(v) ::: inOrder(r)
    }

    def Preorder[A](t: Tree[A]): List[A] = t match {
      case Null => Nil
      case Branch(l, r, v) => List(v) ::: Preorder(l) ::: Preorder(r)
    }

    def postOrder[A](t: Tree[A]): List[A] = t match {
      case Null => Nil
      case Branch(l, r, v) => postOrder(l) ::: postOrder(r) ::: List(v)
    }

    def search[A](t: Tree[A], k: A): Boolean = t match {
      case Null => false
      case Branch(l, r, v) if v != k => search(l, k) || search(r, k)
      case Branch(_, _, v) if v == k => true
    }

    def replace[A](t: Tree[A], before: A, after: A): Tree[A] = t match {
      case Null => Null
      case Branch(l, r, v) if v != before => Branch(replace(l, before, after), replace(r, before, after), v)
      case Branch(l, r, v) if v == before => Branch(replace(l, before, after), replace(r, before, after), after)
    }
  }
}
