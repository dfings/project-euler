package main

import "fmt"

func squareOfSum(max int) int {
	sum := 0
	for i := 1; i <= max; i++ {
		sum += i
	}
	return sum * sum
}

func sumOfSquares(max int) int {
	sum := 0
	for i := 0; i <= max; i++ {
		sum += i * i
	}
	return sum
}

func main() {
	fmt.Printf("%d\n", squareOfSum(100)-sumOfSquares(100))
}
