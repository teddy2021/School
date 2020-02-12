import scala.util.Random
import Problem1._;
import Problem2._;
import Problem3._;


object Test {

  def generate[A](n: Int, default: List[A]) (f: (Int) => A ): List[A] = {
    if (n == 0) f(0)::default
    else generate(n - 1, f(n * Random.nextInt(100))::default)(f)
  }

  def loop[A, B](n: Int, default: B)(f: Int=>B): B = {
    if (n==0) default
    else loop(n-1, f(n))(f)
  }

  def listTotree[A](lst: List[A]): Tree[A] = lst match{
    case Nil => Null
    case head::tail => Branch( listTotree(tail.slice(0, tail.size/2 + 1)), listTotree(tail.slice(tail.size/2 + 1, tail.size)), head)
  }

  def main(args: Array[String]): Unit = {
    val max = 10
    Random.nextInt(max)
    val deck = generate(Random.nextInt(max), List[Int]())((n) => n+1)
    val times = Random.nextInt(max)
    println("List: " + deck)
    println("Inshuffled: " + inshuffle(deck))
    println("Outshuffled: " + outshuffle(deck))
    println("Inshuffled " + times + " times: " + nshuffle(deck, times)(inshuffle _))
    println("Outshuffled " + times + " times " + nshuffle(deck, times)(outshuffle _))
    println(howManyShuffles(deck, reverse(deck))(inshuffle _) + " times to reverse list with inshuffle")
    println(howManyShuffles(outshuffle(deck), deck)(outshuffle(_))  + " times to return to original order with outshuffle")

    val tree = listTotree(deck)
    val value: Int = deck(Random.nextInt(deck.size))
    println(tree)
    println("Tree in order traversal: " + Tree.inOrder(tree))
    println("Tree post order traversal: " + Tree.postOrder(tree))
    println("Tree in pre-order traversal: " + Tree.Preorder(tree))
    val tree2 = Tree.replace(tree, value, value * value)
    println("Tree replacing " + value + " with " + value * value +  ": " + tree2)
    println("New value in new tree: " + Tree.search(tree, value * value))

    val lst = generate(Random.nextInt(max), List[Int]())(n=> n * 2)
    println("List: " + lst)
    println("Repeated halving: " + repHalve(lst))
    println("Binaries: ")
    val bins = loop(lst.size, int2bin(lst(0)))(n => int2bin(lst(n)))
    println(bins)

  }

}
