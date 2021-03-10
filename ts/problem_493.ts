#!/usr/bin/env deno run

const NUM_COLORS = 7;
const NUM_PER_COLOR = 10;
const NUM_TO_PICK = 20;
const SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK;

class Urn {
  slots: number[];

  constructor(slots: number[]) {
    this.slots = slots;
  }

  colorsPicked(): number {
    return this.slots.filter((i) => i < NUM_PER_COLOR).length;
  }

  allPicked(): boolean {
    return this.slots.reduce((sum, current) => sum + current) == SUM_ALL_PICKED;
  }

  cacheKey(): number {
    // Lists don't support non-reference equality checking.
    var newSlots = [...this.slots];
    newSlots.sort();
    let out = 0;
    for (let i = 0; i < newSlots.length; i++) {
      out += Math.pow(11, i) * newSlots[i];
    }
    return out;
  }

  pick(color: number): Urn {
    var newSlots = [...this.slots];
    newSlots[color]--;
    return new Urn(newSlots);
  }
}

type UrnStats = [bigint, bigint];

/**
 * The stats at a given node are the same for any other node with the same color histogram shape,
 * ignoring the specific colors. For example, [1 2 3 4 5 6 7] and [7, 6, 5, 4, 3, 2, 1] will have
 * the same stats due to symmetry. Thus we can memoize computing the stats by sorting the slots.
 */
var urnCache = new Map<number, UrnStats>();
function urnStats(urn: Urn): UrnStats {
  let cacheKey = urn.cacheKey();
  let result = urnCache.get(cacheKey);
  if (result == undefined) {
    if (urn.allPicked()) {
      result = [BigInt(urn.colorsPicked()), 1n];
    } else {
      result = [0n, 0n];
      for (let i = 0; i < NUM_COLORS; i++) {
        for (let j = 0; j < urn.slots[i]; j++) {
          let child_result = urnStats(urn.pick(i));
          result[0] += child_result[0];
          result[1] += child_result[1];
        }
      }
    }
    urnCache.set(cacheKey, result);
  }
  return result;
}

let urn = new Urn(Array<number>(NUM_COLORS).fill(NUM_PER_COLOR));
let stats = urnStats(urn);
console.log("Total colors = " + stats[0]);
console.log("Total picks = " + stats[1]);
console.log("Cache size = " + urnCache.size);
console.log("Average = " + (Number(stats[0] * 1000000000n / stats[1])) / 1000000000);
