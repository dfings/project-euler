#!/usr/bin/env deno run

let total = 0, a = 1, b = 1;
while (b < 4000000) {
  if (b % 2 == 0) {
    total += b;
  }
  b = a + b;
  a = b - a;
}
console.log(total);
