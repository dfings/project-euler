package main

// PrimeFactor returns the list of prime factors of n (including duplicates).
func PrimeFactor(n int) []int {
	var factors []int
	i := 2
	for n > 1 {
		if n%i == 0 {
			n = n / i
			factors = append(factors, i)
		} else {
			i = i + 1
		}
	}
	return factors
}

// Factors returns the list of proper divisors of n, excluding 1.
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
