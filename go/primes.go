package main

type SievePrimeGenerator struct {
	current int
	sieve   map[int][]int
}

func NewSievePrimeGenerator() *SievePrimeGenerator {
	var gen SievePrimeGenerator
	gen.current = 1
	gen.sieve = make(map[int][]int)
	return &gen
}

func (self *SievePrimeGenerator) Next() int {
	for {
		self.current = self.current + 1
		existingFactors := self.sieve[self.current]
		if len(existingFactors) == 0 {
			self.sieve[self.current*self.current] = append(existingFactors, self.current)
			return self.current
		} else {
			delete(self.sieve, self.current)
			for _, factor := range existingFactors {
				compositeFactors := self.sieve[self.current+factor]
				self.sieve[self.current+factor] = append(compositeFactors, factor)
			}
		}
	}
}

type PrimeGenerator struct {
	current int
	primes  []int
}

func NewPrimeGenerator() *PrimeGenerator {
	var gen PrimeGenerator
	gen.current = 1
	gen.primes = make([]int, 0)
	return &gen
}

func (self *PrimeGenerator) isCurrentPrime() bool {
	for _, prime := range self.primes {
		if self.current%prime == 0 {
			return false
		}
		if prime*prime > self.current {
			return true
		}
	}
	return true
}

func (self *PrimeGenerator) Next() int {
	for {
		self.current = self.current + 1
		if self.isCurrentPrime() {
			self.primes = append(self.primes, self.current)
			return self.current
		}
	}
}
