package week2

import common._

import scala.util.Sorting

object Sort {


  def parMergeSort(xs: Array[Int], maxDepth: Int): Unit = {
    val ys = new Array[Int](xs.length)


    def sort(from: Int, until: Int, depth: Int): Unit = {
      if (depth == maxDepth) {
        java.util.Arrays.sort(xs, from, until) //unit - from
      } else {
        val mid = (from + until) / 2
        parallel(sort(from, mid, depth + 1), sort(mid, until, depth + 1))

        val flip = (maxDepth - depth) % 2 == 0
        val src = if (flip) ys else xs
        val dst = if (flip) xs else ys
        merge(src, dst, from, mid, until)
      }
    }

    sort(0, xs.length, 0)

  }

  def merge(src: Array[Int], dst: Array[Int], from: Int, mid: Int, until: Int): Unit = {
    var i, k = from
    var j = mid
    while (i < mid && j < until) {
      if (src(i) < src(j)) {
        dst(k) = src(i)
        i += 1
      } else {
        dst(k) = src(j)
        j += 1
      }
      k += 1
    }
    while (i < mid) {
      dst(k) = src(i)
      i += 1
      k += 1
    }

    while (j < until) {
      dst(k) = src(j)
      j += 1
      k += 1
    }
  }
}
