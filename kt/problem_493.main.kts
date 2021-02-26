#!/usr/bin/env kotlin

import java.math.BigDecimal
import java.math.RoundingMode

val NUM_COLORS = 7
val NUM_PER_COLOR = 10
val NUM_TO_PICK = 20
val SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

/**
 * Represents the state of the urn. The slots are the colors, and the value of the slot is the
 * number of balls left for that color.
*/
class Urn(val slots: List<Int>) {
  fun colorsPicked(): Int = slots.count { it < NUM_PER_COLOR }
  fun allPicked() = slots.sum() == SUM_ALL_PICKED
  fun cacheKey() = slots.sorted()
  fun pick(color: Int) = Urn(slots.mapIndexed { i, count -> if (i == color) count - 1 else count })
}

/**
 * Consider the ways to pick colors from the urn as a tree with depth NUM_TO_PICK and
 * a branching factor of the number of balls left at each node. For any given node, the class
 * encapsulate the stats for all leaves below that node (i.e., all possible ways to pick from this
 * node).
 * @val totalColorsPicks Sum of colors picked across each leaf
 * @val totalPicks Total number of leaves below this node
 */
data class UrnStats(val totalColorsPicked: BigDecimal, val totalPicks: BigDecimal) {
  fun average() = totalColorsPicked.divide(totalPicks, 12, RoundingMode.UP)
  operator fun plus(other: UrnStats) = 
    UrnStats(totalColorsPicked + other.totalColorsPicked, totalPicks + other.totalPicks)
}

/** 
 * The stats at a given node are the same for any other node with the same color histogram shape,
 * ignoring the specific colors. For example, [1 2 3 4 5 6 7] and [7, 6, 5, 4, 3, 2, 1] will have
 * the same stats due to symmetry. Thus we can memoize computing the stats by sorting the slots.
 */
val urnCache = hashMapOf<List<Int>, UrnStats>()
fun urnStats(urn: Urn): UrnStats = urnCache.getOrPut(urn.cacheKey()) {
  if (urn.allPicked()) {
    UrnStats(urn.colorsPicked().toBigDecimal(), 1.toBigDecimal())
  } else {
    // Branch by each possible pick.
    (0..NUM_COLORS - 1).flatMap { color ->
      (1..urn.slots[color]).map { urnStats(urn.pick(color)) }
    }.reduce(UrnStats::plus)
  }
}

val urn = Urn(IntArray(NUM_COLORS) { NUM_PER_COLOR }.asList())
val stats = urnStats(urn)
println("Total colors = ${stats.totalColorsPicked}")
println("Total picks = ${stats.totalPicks}")
println("Cache size = ${urnCache.size}")
println("Average = %.9f".format(stats.average()))
