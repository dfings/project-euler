package main

import (
	"fmt"
	"sort"
)

const (
	numColors    = 7
	numPerColor  = 10
	numToPick    = 20
	sumAllPicked = numColors*numPerColor - numToPick
)

type counts struct {
	colors, picks float64
}

var cache = make(map[[numColors]int]counts)

func allBallsPicked(urn *[numColors]int) bool {
	sum := 0
	for _, v := range urn {
		sum += v
	}
	return sum == sumAllPicked
}

func countColors(urn *[numColors]int) int {
	count := 0
	for _, v := range urn {
		if v != numPerColor {
			count++
		}
	}
	return count
}

func computeCounts(urn *[numColors]int) counts {
	if allBallsPicked(urn) {
		return counts{float64(countColors(urn)), 1}
	}

	var key [numColors]int = *urn
	sort.Ints(key[:])
	c, ok := cache[key]
	if ok {
		return c
	}

	c = counts{0, 0}
	for i := 0; i < numColors; i++ {
		for j := 0; j < urn[i]; j++ {
			urn[i]--
			returned := computeCounts(urn)
			c.colors += returned.colors
			c.picks += returned.picks
			urn[i]++
		}
	}
	cache[key] = c
	return c
}

func main() {
	var urn [numColors]int
	for i := 0; i < numColors; i++ {
		urn[i] = numPerColor
	}
	counts := computeCounts(&urn)
	fmt.Printf("Total colors = %0.0f\n", counts.colors)
	fmt.Printf("Total picks = %0.0f\n", counts.picks)
	fmt.Printf("Average = %0.9f\n", counts.colors/counts.picks)
}
