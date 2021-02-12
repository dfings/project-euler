package main

func PrimeFactor(n int) []int {
	var factors []int
	primes := NewPrimeGenerator()
	for {
		prime := primes.Next()
		if n%prime == 0 {
			factors = append(factors, n)
				for n%prime == 0 {
				n = n / prime
			}
		}
		if n == 1 {
			break
		}
	}
	return factors
}

func Factors(n int) []int {
	var factors []int
	i := 2
	for ; i*i < n; i++ {
		if n%i == 0 {
			factors = append(factors, i)
			factors = append(factors, n/i)
		}
	}
	if i*i == n {
		factors = append(factors, i)
	}
	return factors
}
