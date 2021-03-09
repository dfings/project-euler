#!/usr/bin/env dart

import 'dart:collection';
import 'dart:math';
import 'iterables.dart';

const NUM_COLORS = 7;
const NUM_PER_COLOR = 10;
const NUM_TO_PICK = 20;
const SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK;

class Urn {
  late List<int> slots;

  Urn(List<int> slots) {
    this.slots = slots;
  }

  int colorsPicked() => slots.where((i) => i < NUM_PER_COLOR).length;

  bool allPicked() => slots.sum() == SUM_ALL_PICKED;

  int cacheKey() {
    // Lists
    var newSlots = [...slots];
    newSlots.sort();
    int out = 0;
    for (int i = 0; i < newSlots.length; i++) {
      out += (pow(11, i) * newSlots[i]).toInt();
    }
    return out;
  }

  Urn pick(int color) {
    var newSlots = [...slots];
    newSlots[color]--;
    return new Urn(newSlots);
  }
}

class UrnStats {
  late BigInt totalColorsPicked;
  late BigInt totalPicks;

  UrnStats(BigInt totalColorsPicked, BigInt totalPicks) {
    this.totalColorsPicked = totalColorsPicked;
    this.totalPicks = totalPicks;
  }

  UrnStats plus(UrnStats other) => UrnStats(
      totalColorsPicked + other.totalColorsPicked,
      totalPicks + other.totalPicks);
}

/** 
 * The stats at a given node are the same for any other node with the same color histogram shape,
 * ignoring the specific colors. For example, [1 2 3 4 5 6 7] and [7, 6, 5, 4, 3, 2, 1] will have
 * the same stats due to symmetry. Thus we can memoize computing the stats by sorting the slots.
 */
var urnCache = HashMap<int, UrnStats>();
UrnStats urnStats(Urn urn) => urnCache.putIfAbsent(urn.cacheKey(), () {
      if (urn.allPicked()) {
        return new UrnStats(BigInt.from(urn.colorsPicked()), BigInt.from(1));
      } else {
        // Branch by each possible pick.
        return range(0, NUM_COLORS)
            .expand((color) => range(0, urn.slots[color])
                .map((_) => urnStats(urn.pick(color))))
            .reduce((value, element) => value.plus(element));
      }
    });

void main() {
  var urn = Urn(List<int>.filled(NUM_COLORS, NUM_PER_COLOR));
  var stats = urnStats(urn);
  print("Total colors = ${stats.totalColorsPicked}");
  print("Total picks = ${stats.totalPicks}");
  print("Cache size = ${urnCache.length}");
  print("Average = ${stats.totalColorsPicked / stats.totalPicks}");
}
