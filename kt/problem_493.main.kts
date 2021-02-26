#!/usr/bin/env kotlin

import java.math.BigDecimal
import java.math.RoundingMode

val NUM_COLORS = 7
val NUM_PER_COLOR = 10
val NUM_TO_PICK = 20
val SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

class Urn {
  val slots =  Array<Int>(NUM_COLORS) { NUM_PER_COLOR }
  fun colorsPicked(): Int = slots.count { it < NUM_PER_COLOR }
  fun allPicked() = slots.sum() == SUM_ALL_PICKED
  fun cacheKey() = slots.sorted()
}

data class ChildStats(val totalColors: BigDecimal, val totalLeaves: BigDecimal) {
  fun average() = totalColors.divide(totalLeaves, 12, RoundingMode.UP)
}

val urnCache = hashMapOf<List<Int>, ChildStats>()
fun urnStats(urn: Urn): ChildStats = urnCache.getOrPut(urn.cacheKey()) {
  if (urn.allPicked()) {
    ChildStats(urn.colorsPicked().toBigDecimal(), 1.toBigDecimal())
  } else {
    var totalColors = 0.toBigDecimal()
    var totalLeaves = 0.toBigDecimal()
    for (color in 0..NUM_COLORS-1) {
      for (unused in 1..urn.slots[color]) {
        urn.slots[color] -= 1
        val childStats = urnStats(urn)
        totalColors += childStats.totalColors
        totalLeaves += childStats.totalLeaves
        urn.slots[color] += 1
      }
    }
    ChildStats(totalColors, totalLeaves)
  }
}

val urn = Urn()
val stats = urnStats(urn)
println("Total colors = ${stats.totalColors}")
println("Total picks = ${stats.totalLeaves}")
println("Average = %.9f".format(stats.average()))
