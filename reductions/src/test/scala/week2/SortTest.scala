package week2

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SortTest extends FunSuite {

  test("merge merges a full array") {
    val src = Array[Int](3, 4, 1, 2)
    val dst = Array[Int](0, 0, 0, 0)
    Sort.merge(src, dst, 0, 2, 4)
    assert(dst sameElements Array[Int](1, 2, 3, 4))
  }

  test("merge merges a part of array") {
    val src = Array[Int](3, 4, 1, 2, 1, 2, 5, 6)
    val dst = Array[Int](0, 0, 0, 0, 0, 0, 0, 0)
    Sort.merge(src, dst, 2, 4, 6)
    assert(dst sameElements Array[Int](0, 0, 1, 1, 2, 2, 0, 0))
  }

  test("sort sorts"){
    val src = Array[Int](3, 4, 1, 2)
    Sort.parMergeSort(src, 4)

    assert(src sameElements Array[Int](1,2,3,4))
  }
}
