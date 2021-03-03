package main

import (
	"fmt"
	"math/big"
)

func main() {
	var n big.Int
	n.Exp(big.NewInt(2), big.NewInt(1000), nil)
	fmt.Printf("%d\n", SumOfDigits(n))
}
