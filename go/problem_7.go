package main

import "fmt"

func main() {
	gen := NewPrimeGenerator()
	for i := 0; i < 1000000; i++ {
		gen.Next()
	}
	fmt.Printf("%d\n", gen.Next())
}
