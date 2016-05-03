package main

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

func Factors(n int) []int {
	var factors []int
	i := 2
	for ; i * i < n; i++ {
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
