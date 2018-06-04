package week3

import scala.collection.mutable
import scala.collection.parallel.Task

object Foldd {

  def max(xs: Array[Int]) = {
    xs.par.fold(Int.MinValue)((a: Int, b: Int) => if (a > b) a else b)
  }


  trait Iterator[T] {
    def hasNext: Boolean

    def next(): T

    def foldLeft[S](z: S)(f: (S, T) => S): S = {
      var result = z
      while (hasNext) result = f(result, next())
      result
    }
  }



  trait Traversable[T] {
    def foreach(f: T => Unit): Unit

    def newBuilder: mutable.Builder[T, Traversable[T]]

    def filter(p: T => Boolean): Traversable[T] = {
      val b = newBuilder
      for (e <- this) if (p(e)) b += e
      b.result
    }
  }

}
