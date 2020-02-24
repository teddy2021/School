object Q1 {

  val royalParent = Map("George" -> ("m", "William", "Catherine"), "Charlotte" -> ("f", "William", "Catherine"), "Louis" -> ("m", "William", "Catherine"), "Archie" -> ("m", "Harry", "Meghan"), "Savannah" -> ("f", "Autumn", "Peter"), "Isla" -> ("f", "Autumn", "Peter"), "Mia" -> ("f", "Zara", "Mike"), "Lena" -> ("f", "Zara", "Mike"), "Beatrice" -> ("f", "Andrew", "Sarah"), "Eugenie" -> ("f", "Andrew", "Sarah"), "Louise" -> ("f", "Edward", "Sophie"), "James" -> ("m", "Edward", "Sophie"), "Peter" -> ("m", "Mark", "Anne"), "Zara" -> ("f", "Mark", "Anne"), "William" -> ("m", "Diana", "Charles"), "Harry" -> ("m", "Diana", "Charles"), "Charles" -> ("m", "Elizabeth", "Philip"), "Anne" -> ("f", "Elizabeth", "Philip"), "Andrew" -> ("m", "Elizabeth", "Philip"), "Edward" -> ("m", "Elizabeth", "Philip"), "Elizabeth" -> ("f", "", ""), "Philip" -> ("m", "", ""), "Diana" -> ("f", "", ""), "Mark" -> ("m", "", ""), "Sophie" -> ("f", "", ""), "Sarah" -> ("f", "", ""), "Mike" -> ("m", "", ""), "Autumn" -> ("f", "", ""), "Meghan" -> ("f", "", ""), "Catherine" -> ("f", "", ""), "Timothy" -> ("m", "", ""), "Jack" -> ("m", "", ""), "Camilla" -> ("f", "", ""))


  def parents(p: String): Option[(String, String)] = royalParent.get(p).map(t => List(t._2, t._3)).getOrElse(List()) match{
    case Nil => None
    case List(a, b) => Some(a, b)

  }


  def grandparents(p: String): Option[List[String]] = royalParent.get(p) match{
    case None => None
    case Some((_, a, b: String)) => {
      val gmat: List[String] = royalParent.get(a).map(t => List(t._2, t._3)).getOrElse(List("Untracked", "Untracked")).filter(_ != "")
      val gpat: List[String] = royalParent.get(b).map(t => List(t._2, t._3)).getOrElse(List("Untracked", "Untracked")).filter(_ != "")
      Some(List(gmat(0), gmat(1), gpat(0), gpat(1)))
     }
  }

  def siblings(p: String): Option[List[String]] = {
    List("dog")
  }



  def firstCousins(p: String): Option[List[String]] = {
    // First Cousin => the child of a sibling of a parent
    // child => get(person) = these people
    // sibling => get(person a) = get(person b)

    // get children of siblings of parents
    // get children of children of grandparents

    // loop through map of family:
    //  obtain grandparents
    //  if paternal or maternal grandparents == grandparents of other:
    //    append to list
    val g_parents = grandparents(p).getOrElse(List())
    if(g_parents != List()) {
      lazy val mat = g_parents.slice(0, 1)
      lazy val pat = g_parents.slice(2, 3)

      println(mat, "aaa", pat)
      def go(ppl: List[String], default: List[String]): Option[List[String]] = {
        if (ppl == Nil) {
          None
        }
        else {
          val sz = ppl.size - 1
          val grans = grandparents(ppl(1)).getOrElse(List())
          if (grans != List()) {
            val gmat = grans.slice(0, 1)
            val gpat = grans.slice(2, 3)
            if (ppl(0) != p && (gmat(0) == mat(0) ||
              gmat(1) == mat(0) ||
              gmat(0) == mat(1) ||
              gmat(1) == mat(1) ||
              gmat(0) == pat(0) ||
              gmat(1) == pat(0) ||
              gmat(0) == pat(1) ||
              gmat(1) == pat(1) ||
              gpat(0) == mat(0) ||
              gpat(1) == mat(0) ||
              gpat(0) == mat(1) ||
              gpat(1) == mat(1) ||
              gmat(0) == pat(0) ||
              gmat(1) == pat(0) ||
              gmat(0) == pat(1) ||
              gmat(1) == pat(1))) {
              Some(go(ppl.slice(1, sz), ppl(1) :: default).getOrElse(List()))
            }
            else {
              None
            }
          }
          else{
            None
          }
        }
      }
      go(royalParent.keySet.toList, List())
    }
    else{
      None
    }
  }
/*
  def uncles(p: String): Option[List[String]] = {

  }
*/
  def main(args: Array[String]): Unit = {
    println("The parents of George are: " + parents("George").getOrElse("Nobody"))
    println("The grandparents of George are: " + grandparents("George").getOrElse("Nobody"))
    println("George's first cousins are: " + firstCousins("George").getOrElse("Nobody"))
    }
}
