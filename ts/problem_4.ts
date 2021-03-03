#!/usr/bin/env deno run

function reverse(s: string): string {
  return s.split("").reverse().join("");
}

function isPalindrome(s: string): boolean {
  return s == reverse(s);
}

let value = 0;
for (let i = 100; i < 1000; i++) {
  for (let j = 100; j < 1000; j++) {
    let k = i * j;
    if (isPalindrome(k.toString())) {
      value = Math.max(value, k);
    }
  }
}

console.log(value);
