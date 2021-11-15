#!/usr/bin/env dart

import 'dart:collection';
import 'iterables.dart';
import 'package:collection/collection.dart';

const NUM_COLORS = 7;
const NUM_PER_COLOR = 10;
const NUM_TO_PICK = 20;
const SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK;

class Urn {
  late List<int> slots;

  Urn(List<int> slots) {
    this.slots = slots;
  }

  int get colorsPicked => slots.where((i) => i < NUM_PER_COLOR).length;
  bool get allPicked => slots.sum == SUM_ALL_PICKED;
  List<int> get cacheKey => slots.toList()..sort();
  Urn pick(int color) => Urn(slots.toList()..[color] = slots[color] - 1);
}

class UrnStats {
  late BigInt totalColorsPicked;
  late BigInt totalPicks;

  UrnStats(BigInt totalColorsPicked, BigInt totalPicks) {
    this.totalColorsPicked = totalColorsPicked;
    this.totalPicks = totalPicks;
  }

  UrnStats operator +(UrnStats other) => UrnStats(
      totalColorsPicked + other.totalColorsPicked,
      totalPicks + other.totalPicks);
}

/** 
 * The stats at a given node are the same for any other node with the same color histogram shape,
 * ignoring the specific colors. For example, [1 2 3 4 5 6 7] and [7, 6, 5, 4, 3, 2, 1] will have
 * the same stats due to symmetry. Thus we can memoize computing the stats by sorting the slots.
 */
UrnStats computeUrnStats(Urn urn) {
  if (urn.allPicked) {
    return UrnStats(BigInt.from(urn.colorsPicked), BigInt.from(1));
  } else {
    // Branch by each possible pick.
    return range(0, NUM_COLORS)
        .expand((color) =>
            range(0, urn.slots[color]).map((_) => urnStats(urn.pick(color))))
        .reduce((a, b) => a + b);
  }
}

const eq = ListEquality();
final urnCache = HashMap<List<int>, UrnStats>(
    equals: eq.equals, hashCode: eq.hash, isValidKey: eq.isValidKey);
UrnStats urnStats(Urn urn) =>
    urnCache.putIfAbsent(urn.cacheKey, () => computeUrnStats(urn));

void main() {
  final urn = Urn(List<int>.filled(NUM_COLORS, NUM_PER_COLOR));
  final stats = urnStats(urn);
  print("Total colors = ${stats.totalColorsPicked}");
  print("Total picks = ${stats.totalPicks}");
  print("Cache size = ${urnCache.length}");
  print("Average = ${stats.totalColorsPicked / stats.totalPicks}");
}
