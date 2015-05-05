package main

import "fmt"

func main() {
	factors := PrimeFactor(600851475143)
	max := 0
	for i := 0; i < len(factors); i++ {
		if factors[i] > max {
			max = factors[i]
		}
	}
	fmt.Printf("%d\n", max)
}
