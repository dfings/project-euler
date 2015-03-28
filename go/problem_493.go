package main

import (
	"fmt"
	"sort"
)

const (
	NumColors = 7
	NumPerColor = 10
	NumToPick = 20
)

type Counts struct {
	colors, picks float64
}

type Solver struct {
	ballsPicked int
	urn [NumColors]int
	memoized map[[NumColors]int]Counts
}

func NewSolver() *Solver {
	var solver Solver
	solver.memoized = make(map[[NumColors]int]Counts)
	for i := 0; i < NumColors; i++ {
		solver.urn[i] = NumPerColor
	}
	return &solver
}

func (self *Solver) countColors() int {
	count := 0
	for i := 0; i < NumColors; i++ {
		if (self.urn[i] != NumPerColor) {
			count++
		}
	}
	return count
}

func (self *Solver) pickNextBall() Counts {
	if (self.ballsPicked == NumToPick) {
		return Counts{float64(self.countColors()), 1}
	}
	
	var encoded [NumColors]int = self.urn
	sort.Ints(encoded[:])
	counts, ok := self.memoized[encoded]
	if (ok) {
		return counts
	}
	
	counts = Counts{0, 0}
	self.ballsPicked++
	for i:= 0; i < NumColors; i++ {
		for j := 0; j < NumPerColor; j++ {
			if (self.urn[i] > j) {
				self.urn[i]--
				returned := self.pickNextBall()
				counts.colors += returned.colors
				counts.picks += returned.picks
				self.urn[i]++
			}
		}
	}
	self.ballsPicked--
	self.memoized[encoded] = counts
	return counts
}

func main() {
	solver := NewSolver()
	counts := solver.pickNextBall()
	fmt.Printf("Total colors = %0.0f\n", counts.colors)
	fmt.Printf("Total picks = %0.0f\n", counts.picks)
	fmt.Printf("Average = %0.9f\n", counts.colors / counts.picks)
}
