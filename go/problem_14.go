package main

import "fmt"

var cache = make(map[int]int)

func getNext(n int) int {
	if n%2 == 0 {
		return n / 2
	} else {
		return 3*n + 1
	}
}

func computeAndCache(n int) int {
	if n == 1 {
		return 1
	}
	length, ok := cache[n]
	if ok {
		return length
	} else {
		next := getNext(n)
		length = 1 + computeAndCache(next)
		cache[n] = length
		return length
	}
}

func main() {
	max_n := -1
	max_length := 0
	for n := 1; n <= 1000000; n++ {
		length := computeAndCache(n)
		if length > max_length {
			max_n = n
			max_length = length
		}
	}
	fmt.Printf("%d %d\n", max_length, max_n)
}
