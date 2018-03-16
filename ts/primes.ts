export function* primes() {
  let i = 2;
  let sieve = new Map<number, Array<number>>();

  while (true) {
    let res = sieve.get(i);
    if (res == null) {
      yield i;
      sieve.set(i * i, [i]);
    } else {
      sieve.delete(i);
      for (let j of res) {
        let res2 = sieve.get(i + j);
        if (res2 == null) {
          res2 = [];
          sieve.set(i + j, res2);
        }
        res2.push(j);
      }
    }
    i += 1
  }
}
