package main

import "fmt"

func isDivisibleBy(i int, primes []int) bool {
  for _, prime := range primes {
    if i % prime == 0 {
      return true
    }
    if prime * prime > i {
      return false
    }
  }
  return false
}

func getNthPrime(n int) int {
  var primes []int;
  i := 2
  for {
    if !(isDivisibleBy(i, primes)) {
      primes = append(primes, i)
    }
    if len(primes) == n {
      return primes[n - 1]
    }
    i += 1
  }
}

func main() {
  fmt.Printf("%d\n", getNthPrime(10001))
}
