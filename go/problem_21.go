package main

import "fmt"

var cache = make(map[int]int)

func d(n int) int {
	var sum, ok = cache[n]
	if !ok {
		sum = 1
		for _, v := range Factors(n) {
			sum += v
		}
		cache[n] = sum
	}
	return sum
}

func main() {
	sum := 0
	for i := 0; i < 10000; i++ {
		if i == d(d(i)) && i != d(i) {
			sum += i
		}
	}
	fmt.Printf("%d\n", sum)
}
