package main

import (
	"fmt"
	"math/big"
)

func main() {
	var v big.Int
	v.MulRange(2, 100)
	fmt.Printf("%d\n", SumOfDigits(v))
}
