package main

import "fmt"

func main() {
	sum := 0
	a, b := 1, 1
	for b < 4000000 {
		if b%2 == 0 {
			sum += b
		}
		a, b = b, a+b
	}
	fmt.Printf("%d\n", sum)
}
