import { primes } from "./primes";

export function prime_factors(n: number): Array<number> {
  let factors = new Array<number>();
  for (let prime of primes()) {
    if (n % prime == 0) {
      factors.push(prime);
      while (n % prime == 0) {
        n /= prime;
      }
    }
    if (n == 1) {
      return factors;
    }
  }
}
