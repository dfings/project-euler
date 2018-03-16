import { primes } from "./primes";

export function prime_factors(n: number): Array<number> {
  let factors = new Array<number>();
  let p = primes();
  while (true) {
    let prime = p.next().value;
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
