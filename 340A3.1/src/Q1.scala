object Q1 {

  val royalParent = Map("George" -> ("m", "William", "Catherine"), "Charlotte" -> ("f", "William", "Catherine"), "Louis" -> ("m", "William", "Catherine"), "Archie" -> ("m", "Harry", "Meghan"), "Savannah" -> ("f", "Autumn", "Peter"), "Isla" -> ("f", "Autumn", "Peter"), "Mia" -> ("f", "Zara", "Mike"), "Lena" -> ("f", "Zara", "Mike"), "Beatrice" -> ("f", "Andrew", "Sarah"), "Eugenie" -> ("f", "Andrew", "Sarah"), "Louise" -> ("f", "Edward", "Sophie"), "James" -> ("m", "Edward", "Sophie"), "Peter" -> ("m", "Mark", "Anne"), "Zara" -> ("f", "Mark", "Anne"), "William" -> ("m", "Diana", "Charles"), "Harry" -> ("m", "Diana", "Charles"), "Charles" -> ("m", "Elizabeth", "Philip"), "Anne" -> ("f", "Elizabeth", "Philip"), "Andrew" -> ("m", "Elizabeth", "Philip"), "Edward" -> ("m", "Elizabeth", "Philip"), "Elizabeth" -> ("f", "", ""), "Philip" -> ("m", "", ""), "Diana" -> ("f", "", ""), "Mark" -> ("m", "", ""), "Sophie" -> ("f", "", ""), "Sarah" -> ("f", "", ""), "Mike" -> ("m", "", ""), "Autumn" -> ("f", "", ""), "Meghan" -> ("f", "", ""), "Catherine" -> ("f", "", ""), "Timothy" -> ("m", "", ""), "Jack" -> ("m", "", ""), "Camilla" -> ("f", "", ""))


  def parents(p: String): Option[(String, String)] = {
    val res = royalParent.get(p)
    val rents = res.getOrElse((Nil,Nil,Nil))
    if(res == None){
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
    case Some((_, a, b: String)) => {
      val gmat: List[String] = parents(a).map(t => List(t._1, t._2)).getOrElse(List("Untracked", "Untracked"))
      val gpat: List[String] = parents(b).map(t=>List(t._1, t._2)).getOrElse(List("Untracked", "Untracked"))
      println(gmat, "aaa", gpat)
      Some(List(gmat(0), gmat(1), gpat(0), gpat(1)))
     }
  }

  def siblings(p: String): Option[List[String]] = {
    val parent = parents(p).getOrElse(("Untracked", "Untracked"))
    val family = royalParent.keySet.toList
    def go(ppl: List[String], default: List[String]): Option[List[String]] ={

      if(ppl != Nil) {
        val sz = ppl.size
        val child = ppl(0)
        val ancestor = parents(child).getOrElse(("Untracked", "Untracked"))
        if (child != p && parent == ancestor) {
          go(ppl.slice(1, sz), child::default)
        }
        else{
          go(ppl.slice(1, sz), default)
        }
      }
      else{
        if(default == List()){None}
        else{Some(default)}
      }

    }
    go(family, List())
  }



  def firstCousins(p: String): Option[List[String]] = {
    Some(List("Dog"))
  }

  def uncles(p: String): Option[List[String]] = {
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
