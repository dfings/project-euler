#!/usr/bin/env deno run

function gcd(x: number, y: number): number {
  while (y != 0) {
    let tmp = x;
    x = y;
    y = tmp % y;
  }
  return x;
}

function lcm(x: number, y: number): number {
  return (x * y) / gcd(x, y);
}

let n = 1
for (let i = 2; i <= 20; i++) {
	n = lcm(n, i)
}
console.log(n)
