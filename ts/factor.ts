export function prime_factors(n: number): Array<number> {
  let factors = new Array<number>();
  let i = 2;
  while (n > 1) {
    if (n % i == 0) {
      factors.push(i);
      n /= i
    } else {
      i += 1
    }
  }
  return factors;
}
