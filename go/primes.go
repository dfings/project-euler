package main

type PrimeGenerator struct {
	current int
	sieve   map[int][]int
}

func NewPrimeGenerator() *PrimeGenerator {
	var primes PrimeGenerator
	primes.current = 1
	primes.sieve = make(map[int][]int)
	return &primes
}

func (self *PrimeGenerator) Next() int {
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
