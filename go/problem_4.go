package main

import (
	"fmt"
	"strconv"
)

func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for {
		if s[i] != s[j] {
			return false
		} else if i > j {
			return true
		}
		i = i + 1
		j = j - 1
	}
	return true
}

func main() {
	max := 0
	for i := 999; i > 99; i-- {
		for j := 999; j > 99; j-- {
			current := i * j
			if isPalindrome(strconv.Itoa(current)) && current > max {
				max = current
			}
		}
	}
	fmt.Printf("%d\n", max)
}
