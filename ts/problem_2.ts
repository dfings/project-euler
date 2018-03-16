#!/usr/bin/env ts-node

let total = 0, a = 1, b = 1;
while (b < 4000000) {
  if (b % 2 == 0) {
    total += b;
  }
  a = b;
  b = a + b;
}
console.log(total);
