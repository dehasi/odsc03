package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer (new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  sealed abstract class Tree[A]

  case class Leaf[A](value: A) extends Tree[A]

  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  def reduce[A](t: Tree[A], f: (A, A) => A): A = t match {
    case Leaf(v) => v
    case Node(l, r) => {
      val (lv, rv) = parallel(reduce(l, f), reduce(r, f))
      f(lv, rv)
    }
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def isOpenBrace(char: Char) = char == '('

  def isClosedBrace(char: Char) = char == ')'

  def isBrace(char: Char) = isOpenBrace(char) || isClosedBrace(char)

  def balance(chars: Array[Char]): Boolean = {

    def balance(chars: Array[Char], stack: Int): Boolean = {
      if (chars.isEmpty) stack.equals(0)
      else if (isOpenBrace(chars.head)) balance(chars.tail, stack + 1)
      else if (stack == 0) false
      else balance(chars.tail, stack - 1)
    }

    val list = chars.filter(c => isBrace(c))
    balance(list, 0)
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, open: Int, closed: Int): (Int, Int) = {
      var o = open
      var c = closed
      var i = idx
      while(i <  until) {
        if (isOpenBrace(chars(i))) o += 1
        if (isClosedBrace(chars(i))) c += 1
        i +=1
      }
      (o, c)
    }

    def reduce(from: Int, until: Int): (Int, Int) = {
      if (until-from <= threshold) {
        traverse(from,until,0,0)
      }
      else {
        val mid = (from+until)/2
        val (lv, rv) = parallel(reduce(from,mid),reduce(mid,until))

        (lv._1 + rv._1, lv._2 + rv._2)
      }
    }

    val tuple = reduce(0, chars.length)
    tuple._1 == tuple._2
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
