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

data class UrnStats(val totalColorsPicked: BigDecimal, val totalPicks: BigDecimal) {
  fun average() = totalColorsPicked.divide(totalPicks, 12, RoundingMode.UP)
}

val urnCache = hashMapOf<List<Int>, UrnStats>()
fun urnStats(urn: Urn): UrnStats = urnCache.getOrPut(urn.cacheKey()) {
  if (urn.allPicked()) {
    UrnStats(urn.colorsPicked().toBigDecimal(), 1.toBigDecimal())
  } else {
    var totalColorsPicked = 0.toBigDecimal()
    var totalPicks = 0.toBigDecimal()
    for (color in 0..NUM_COLORS-1) {
      for (unused in 1..urn.slots[color]) {
        val child = urn.pick(color)
        val childStats = urnStats(child)
        totalColorsPicked += childStats.totalColorsPicked
        totalPicks += childStats.totalPicks
      }
    }
    UrnStats(totalColorsPicked, totalPicks)
  }
}

val urn = Urn(IntArray(NUM_COLORS) { NUM_PER_COLOR }.asList())
val stats = urnStats(urn)
println("Total colors = ${stats.totalColorsPicked}")
println("Total picks = ${stats.totalPicks}")
println("Average = %.9f".format(stats.average()))
