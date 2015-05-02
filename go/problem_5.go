package main

import "fmt"

func gcd(x int, y int) int {
	for y != 0 {
		x, y = y, x%y
	}
	return x
}

func lcm(x int, y int) int {
	return (x * y) / gcd(x, y)
}

func main() {
	n := 1
	for i := 2; i <= 20; i++ {
		n = lcm(n, i)
	}
	fmt.Printf("%d\n", n)
}
