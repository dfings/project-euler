package main

import (
	"fmt"
	"sort"
)

const (
	NumColors = 7
	NumPerColor = 10
	NumToPick = 20
	SumAllPicked = NumColors * NumPerColor - NumToPick
)

type Counts struct {
	colors, picks float64
}

var cache = make(map[[NumColors]int]Counts)

func allBallsPicked(urn *[NumColors]int) bool {
	sum := 0
	for i := 0; i < NumColors; i++ {
		sum += urn[i]
	}
	return sum == SumAllPicked
}

func countColors(urn *[NumColors]int) int {
	count := 0
	for i := 0; i < NumColors; i++ {
		if (urn[i] != NumPerColor) {
			count++
		}
	}
	return count
}

func computeCounts(urn *[NumColors]int) Counts {
	if (allBallsPicked(urn)) {
		return Counts{float64(countColors(urn)), 1}
	}
	
	var key [NumColors]int = *urn
	sort.Ints(key[:])
	counts, ok := cache[key]
	if (ok) {
		return counts
	}
	
	counts = Counts{0, 0}
	for i:= 0; i < NumColors; i++ {
		for j := 0; j < urn[i]; j++ {
			urn[i]--
			returned := computeCounts(urn)
			counts.colors += returned.colors
			counts.picks += returned.picks
			urn[i]++
		}	
	}
	cache[key] = counts
	return counts
}

func main() {
	var urn [NumColors]int;
	for i := 0; i < NumColors; i++ {
		urn[i] = NumPerColor
	}
	counts := computeCounts(&urn)
	fmt.Printf("Total colors = %0.0f\n", counts.colors)
	fmt.Printf("Total picks = %0.0f\n", counts.picks)
	fmt.Printf("Average = %0.9f\n", counts.colors / counts.picks)
}
