#!/usr/bin/env kotlin

import java.math.BigDecimal
import java.math.RoundingMode

val NUM_COLORS = 7
val NUM_PER_COLOR = 10
val NUM_TO_PICK = 20
val SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

class Urn(val slots: List<Int>) {
  fun colorsPicked(): Int = slots.count { it < NUM_PER_COLOR }
  fun allPicked() = slots.sum() == SUM_ALL_PICKED
  fun cacheKey() = slots.sorted()
  fun pick(color: Int) = Urn(slots.mapIndexed { i, count -> if (i == color) count - 1 else count })
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
        val child = urn.pick(color)
        val childStats = urnStats(child)
        totalColors += childStats.totalColors
        totalLeaves += childStats.totalLeaves
      }
    }
    ChildStats(totalColors, totalLeaves)
  }
}

val urn = Urn(IntArray(NUM_COLORS) { NUM_PER_COLOR }.asList())
val stats = urnStats(urn)
println("Total colors = ${stats.totalColors}")
println("Total picks = ${stats.totalLeaves}")
println("Average = %.9f".format(stats.average()))
