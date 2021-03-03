#!/usr/bin/env deno run

import { fibonacci } from "./fibonacci.ts";

let total = 0n;
for (let term of fibonacci()) {
  if (term > 4000000n) {
    break;
  } else if (term % 2n == 0n) {
    total += term;
  }
}
console.log(total);
