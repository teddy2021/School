import scala.util.Random
import Problem1._;
import Problem2._;
import Problem3._;


object Test {

  val max = 50

  def generate[A](n: Int, default: List[A]) (f: (Int) => A ): List[A] = {
    if (n == 0) f(0)::default
    else generate(n - 1, f(n * Random.nextInt(100))::default)(f)
  }

  def getBins(l : List[Int]): List[List[Int]] = l match{
    case Nil => Nil
    case h::t => int2bin(h) ::getBins(t)
  }

  def generateTree(n: Int): Tree[Int] = {
    if (n == 0) Null
    else Branch(generateTree(n/2), generateTree(n/2), n * Random.nextInt(max))
  }

  def main(args: Array[String]): Unit = {
    Random.nextInt(max)
    val deck = generate(Random.nextInt(max) + 10, List[Int]())((n) => n+1)
    val times = Random.nextInt(max)
    println("List: " + deck + " with size " + deck.size + " and middle element " + deck(deck.size/2))
    println("Split in half: " + Split(deck, deck.size/2))
    println("Slit unevenly: " + Split(deck, deck.size/4))
    println("Inshuffled: " + inshuffle(deck))
    println("Outshuffled: " + outshuffle(deck))
    println("Inshuffled " + times + " times: " + nshuffle(deck, times)(inshuffle _))
    println("Outshuffled " + times + " times " + nshuffle(deck, times)(outshuffle _))
    if(deck.size % 2 == 0) {
      println(howManyShuffles(deck, reverse(deck))(inshuffle _) + " times to reverse list with inshuffle")
      println(howManyShuffles(outshuffle(deck), deck)(outshuffle(_)) + " times to return to original order with outshuffle")
    }
    else{
      println(howManyShuffles(deck.slice(0, deck.size - 1), reverse(deck.slice(0, deck.size - 1)))(inshuffle _) + " times to reverse list with inshuffle (adjusted to be even)")
      println(howManyShuffles(outshuffle(deck.slice(0,deck.size - 1)), deck.slice(0, deck.size - 1))(outshuffle(_)) + " times to return to original order with outshuffle (adjusted to be even)")
    }
    val tree = generateTree(Random.nextInt(max) + 5)
    val value: Int = Random.nextInt(max)
    println(tree)
    println("Tree in order traversal: " + Tree.inOrder(tree))
    println("Tree post order traversal: " + Tree.postOrder(tree))
    println("Tree in pre-order traversal: " + Tree.Preorder(tree))
    println("Value " + value + " in new tree: " + Tree.search(tree, value))
    val tree2 = Tree.replace(tree, value, value * value)
    println("Tree replacing " + value + " with " + value * value +  ": " + tree2)

    val lst = generate(Random.nextInt(max) + 5, List[Int]())(n=> n * 2)
    println("List2: " + lst)
    println("Repeated halving: " + repHalve(lst))
    println("Binaries: ")
    val bins = getBins(lst)
    println(bins)

  }

}
