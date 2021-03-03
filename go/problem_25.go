package main

import (
	"fmt"
	"math/big"
)

func main() {
	a, b := big.NewInt(1), big.NewInt(1)
	var limit big.Int
	limit.Exp(big.NewInt(10), big.NewInt(999), nil)
	count := 1
	for a.Cmp(&limit) < 0 {
		count++
		b.Add(a, b)
		a.Sub(b, a)
	}
	fmt.Printf("%d\n", count)
}
