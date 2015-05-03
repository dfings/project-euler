package main

import (
	"bufio"
	"fmt"
	"math/big"
	"os"
)

func main() {
	file, _ := os.Open(os.Args[1])
	defer file.Close()

	scanner := bufio.NewScanner(file)
	sum := big.NewInt(1)
	for scanner.Scan() {
		var val big.Int
		val.UnmarshalText(scanner.Bytes())
		sum.Add(sum, &val)
	}
	fmt.Println(sum.String()[0:10])
}
