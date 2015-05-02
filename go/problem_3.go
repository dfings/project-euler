package main

import "fmt"

func main() {
	factor, max := 2, 0
	num := 600851475143
	for num > 1 {
		if num%factor == 0 {
			num = num / factor
			max = factor
		} else {
			factor = factor + 1
		}
	}
	fmt.Printf("%d\n", max)
}
