package main

import "fmt"

func main() {
	primes := NewPrimeGenerator()
	for i := 0; i < 10000; i++ {
		primes.Next()
	}
	fmt.Printf("%d\n", primes.Next())
}
