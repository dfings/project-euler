package main

import "fmt"

func main() {
	n := 10001
	fmt.Printf("%d\n", GeneratePrimes(n)[n-1])
}
