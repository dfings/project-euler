package main

import (
	"fmt"
	"math/big"
	"sort"
)

const (
	numColors    = 7
	numPerColor  = 10
	numToPick    = 20
	sumAllPicked = numColors*numPerColor - numToPick
)

type counts struct {
	colors, picks *big.Int
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
		if v < numPerColor {
			count++
		}
	}
	return count
}

func computeCounts(urn *[numColors]int) counts {
	var key [numColors]int = *urn
	sort.Ints(key[:])
	c, ok := cache[key]
	if ok {
		return c
	}

	if allBallsPicked(urn) {
		c = counts{big.NewInt(int64(countColors(urn))), big.NewInt(1)}
	} else {
		c = counts{big.NewInt(0), big.NewInt(0)}
		for i := 0; i < numColors; i++ {
			for j := 0; j < urn[i]; j++ {
				urn[i]--
				returned := computeCounts(urn)
				c.colors.Add(c.colors, returned.colors)
				c.picks.Add(c.picks, returned.picks)
				urn[i]++
			}
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
	fmt.Printf("Total colors = %s\n", counts.colors.String())
	fmt.Printf("Total picks = %s\n", counts.picks.String())
	fmt.Printf("Cache size = %d\n", len(cache))
	average := new(big.Float).Quo(
		new(big.Float).SetInt(counts.colors),
		new(big.Float).SetInt(counts.picks))
	fmt.Printf("Average = %0.9f\n", average)
}
