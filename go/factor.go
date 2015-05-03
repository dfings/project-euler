package main

func Factor(n int) []int {
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
