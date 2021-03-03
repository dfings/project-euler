package main

import (
	"fmt"
	"math/big"
)

var cache = make(map[[2]int]int)

func countPaths(x int, y int) int {
	key := [2]int{x, y}
	val, ok := cache[key]
	if !ok {
		if x < 0 || y < 0 {
			val = 0
		} else if x == 0 && y == 0 {
			val = 1
		} else {
			val = countPaths(x-1, y) + countPaths(x, y-1)
		}
		cache[key] = val
	}
	return val
}

func usingCount() {
	fmt.Printf("%d\n", countPaths(20, 20))
}

func usingChoose() {
	var num, denom big.Int
	num.MulRange(2, 40)
	denom.MulRange(2, 20)
	denom.Mul(&denom, &denom)
	num.Div(&num, &denom)
	fmt.Printf("%s\n", num.String())
}

func main() {
	usingCount()
	usingChoose()
}
