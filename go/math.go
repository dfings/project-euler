package main

import "math/big"

var zero = big.NewInt(0)
var ten = big.NewInt(10)

// SumOfDigits sums all the digits in big.Int.
func SumOfDigits(n big.Int) int {
	sum := big.NewInt(0)
	var r big.Int
	for n.Cmp(zero) > 0 {
		n.DivMod(&n, ten, &r)
		sum.Add(sum, &r)
	}
	return int(sum.Int64())
}
