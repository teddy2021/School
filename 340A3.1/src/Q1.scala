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
  def build[A](p: String, group: List[String], default_return: List[String], default: A)(h: String => Option[A])(f: (A, A) => Boolean): List[String] = group match{
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
    Some(
      royalParent.keySet.toList
        .filter(grandparents(_).getOrElse(Nil).filter(_ != "Untracked") != Nil) // remove people with untracked grandparents
        .filter(grandparents(_).getOrElse(Nil).filter(_ != "Untracked") == grandparents(p).getOrElse(Nil).filter(_ != "Untracked")) // remove people who do not share grandparents
        .filter(parents(p).getOrElse(Nil) != parents(_).getOrElse(Nil))
    )
  }

  def uncles(p: String): Option[List[String]] = {
    Some(royalParent.keySet.toList // convert keyset to list
      .filter(royalParent.get(_).getOrElse("","","")._1 == "m") // remove the women
      .filter(parents(_).map(t => List(t._1, t._2)).getOrElse(Nil).filter(_ != "Untracked") != Nil) // remove the people with untracked grandparents
      .filter(grandparents(p).getOrElse(Nil).filter(_ != "Untracked") == parents(_).map(t => List(t._1, t._2)).getOrElse(Nil).filter(_ != "Untracked")) // remove the people who do not share a grandparents
      .filter(!parents(p).map(t => List(t._1, t._2)).getOrElse(Nil).contains(_))) // remove the person's own parent
  }

  def loop(ppl: List[String]): Unit = ppl match{
    case a::b => {
      println("==========", a, "==========")
      println("The parents of " + a + " are: " + parents(a).getOrElse("Nobody"))
      println("The grandparents of " + a + " are: " + grandparents(a).getOrElse("Nobody"))
      println("The siblings of " + a + " are: " + siblings(a).getOrElse("Nobody"))
      println(a + "'s first cousins are: " + firstCousins(a).getOrElse("Nobody"))
      println(a + "'s uncles are: " + uncles(a).getOrElse("Nobody"))
      println()
      loop(b)
    }
    case Nil => println("Done")
  }

  def main(args: Array[String]): Unit = {
    val family = royalParent.keySet.toList
    loop(family)
    val gps = grandparents("Zara").getOrElse(Nil).filter(_ != "Untracked")
    val ps = parents("Charles").map(t => List(t._1, t._2)).getOrElse(Nil).filter(_ != "Untracked")
    println(gps)
    println(ps)
    println(gps == ps)

  }
}
