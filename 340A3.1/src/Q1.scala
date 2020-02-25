import scala.annotation.tailrec

object Q1 {

  val royalParent = Map("George" -> ("m", "William", "Catherine"), "Charlotte" -> ("f", "William", "Catherine"), "Louis" -> ("m", "William", "Catherine"), "Archie" -> ("m", "Harry", "Meghan"), "Savannah" -> ("f", "Autumn", "Peter"), "Isla" -> ("f", "Autumn", "Peter"), "Mia" -> ("f", "Zara", "Mike"), "Lena" -> ("f", "Zara", "Mike"), "Beatrice" -> ("f", "Andrew", "Sarah"), "Eugenie" -> ("f", "Andrew", "Sarah"), "Louise" -> ("f", "Edward", "Sophie"), "James" -> ("m", "Edward", "Sophie"), "Peter" -> ("m", "Mark", "Anne"), "Zara" -> ("f", "Mark", "Anne"), "William" -> ("m", "Diana", "Charles"), "Harry" -> ("m", "Diana", "Charles"), "Charles" -> ("m", "Elizabeth", "Philip"), "Anne" -> ("f", "Elizabeth", "Philip"), "Andrew" -> ("m", "Elizabeth", "Philip"), "Edward" -> ("m", "Elizabeth", "Philip"), "Elizabeth" -> ("f", "", ""), "Philip" -> ("m", "", ""), "Diana" -> ("f", "", ""), "Mark" -> ("m", "", ""), "Sophie" -> ("f", "", ""), "Sarah" -> ("f", "", ""), "Mike" -> ("m", "", ""), "Autumn" -> ("f", "", ""), "Meghan" -> ("f", "", ""), "Catherine" -> ("f", "", ""), "Timothy" -> ("m", "", ""), "Jack" -> ("m", "", ""), "Camilla" -> ("f", "", ""))


  def parents(p: String): Option[(String, String)] = {
    val res = royalParent.get(p)
    val rents = res.getOrElse((Nil,Nil,Nil))
    if(res.isEmpty){
      None
    }
    else{
      if(rents._2 != "" && rents._3 != "") {
        res.map(t => Tuple2(t._2, t._3))
      }
      else{
        None
      }
    }
  }


  def grandparents(p: String): Option[List[String]] = royalParent.get(p) match{
    case None => None
    case Some((_, a, b: String)) =>
      val gmat: List[String] = parents(a).map(t => List(t._1, t._2)).getOrElse(List("Untracked", "Untracked"))
      val gpat: List[String] = parents(b).map(t=>List(t._1, t._2)).getOrElse(List("Untracked", "Untracked"))
      Some(List(gmat.head, gmat(1), gpat.head, gpat(1)))

  }


  @tailrec
  def build[A, B](p: String, group: List[String], default_return: List[String], default: A)(h: String => Option[A])(f: (A, A) => Boolean): List[String] = group match{
    case Nil => default_return
    case a::b =>
      val first = h(p).getOrElse(default)
      val second = h(a).getOrElse(default)
      if(f(first, second)){
        build(p, b, a::default_return, default)(h)(f)
      }
      else{build(p, b, default_return, default)(h)(f)}

  }

  def siblings(p: String): Option[List[String]] = {
    val family = royalParent.keySet.toList.filter(s => s != p)
    Some(build(p, family, List(), ("Untracked", "Untracked"))(parents)((x,y) => x==y && x != ("Untracked", "Untracked")))
  }

  def firstCousins(p: String): Option[List[String]] = {
    val family = royalParent.keySet.toList.filter(s => s != p && !siblings(p).contains(s))
    val z: List[String] = Nil
    Some(build(p, family, z, z)(grandparents)((x, y) => x.filter(!y.contains(_)) == Nil).filter(!siblings(p).getOrElse(Nil).contains(_)))
  }

  def uncles(p: String): Option[List[String]] = {
    val family = royalParent.keySet.toList.filter(s => s != p)
    val z: List[String] = Nil
    Some(List("Dog"))

  }

  def main(args: Array[String]): Unit = {
    println("The parents of George are: " + parents("George").getOrElse("Nobody"))
    println("The grandparents of George are: " + grandparents("George").getOrElse("Nobody"))
    println("The siblings of George are: " + siblings("George").getOrElse("Nobody"))
    println("George's first cousins are: " + firstCousins("George").getOrElse("Nobody"))
    println("George's uncles are: " + uncles("George").getOrElse("Nobody"))
    }
}
