package main

func isDivisibleBy(i int, primes []int) bool {
	for _, prime := range primes {
		if i%prime == 0 {
			return true
		}
		if prime*prime > i {
			return false
		}
	}
	return false
}

func GeneratePrimes(n int) []int {
	var primes []int
	i := 2
	for {
		if !(isDivisibleBy(i, primes)) {
			primes = append(primes, i)
		}
		if len(primes) == n {
			return primes
		}
		i += 1
	}
}
