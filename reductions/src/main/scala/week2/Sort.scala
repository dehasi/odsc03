package week2

import common._

import scala.util.Sorting

trait Sort {
  def parMergeSort(xs: Array[Int], maxDepth: Int): Unit = {
    val ys = new Array[Int](xs.length)

    def sort(from: Int, until: Int, depth: Int): Unit = {
      if (depth == maxDepth) {
        java.util.Arrays.sort(xs, from, from - until)
      } else {
        val mid = (from + until) / 2
        parallel(sort(from, mid, depth + 1), sort(mid, until, depth + 1))

        val flip = (maxDepth - depth) % 2 == 0
        val src = if (flip) ys else xs
        val dst = if (flip) xs else ys
        //merge
      }
    }

  }
}