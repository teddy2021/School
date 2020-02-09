object Problem3 {

  def unfold[A, B](p: A=>Boolean, h: A=>B, t: A=>A)(x: A): List[B] = {
    if(p(x)) Nil
    else h(x)::unfold(p, h, t)(t(x))
  }

  def int2bin(x: Int): Int ={
    unfold((n==0), (n%2), (n))
  }
}
