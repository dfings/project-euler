package main

import (
	"fmt"
	"strconv"
)

func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i <= j {
		if s[i] != s[j] {
			return false
		}
		i = i + 1
		j = j - 1
	}
	return true
}

func main() {
	max := 0
	for i := 100; i < 1000; i++ {
		for j := 100; j < 1000; j++ {
			current := i * j
			if isPalindrome(strconv.Itoa(current)) && current > max {
				max = current
			}
		}
	}
	fmt.Printf("%d\n", max)
}
