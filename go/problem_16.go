package main

import (
	"fmt"
	"math/big"
)

func main() {
	zero, ten, sum := big.NewInt(0), big.NewInt(10), big.NewInt(0)
	var n, r big.Int
	n.Exp(big.NewInt(2), big.NewInt(1000), nil)
	for n.Cmp(zero) > 0 {
		n.DivMod(&n, ten, &r)
		sum.Add(sum, &r)
	}
	fmt.Printf("%s\n", sum.String())
}
