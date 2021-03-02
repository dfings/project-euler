package main

// PrimeGenerator stores the state needed to produce an infinite iterator of primes.
type PrimeGenerator struct {
	current int
	sieve   map[int][]int
}

// NewPrimeGenerator creates the state of the PrimeGenerator starting at 1.
func NewPrimeGenerator() *PrimeGenerator {
	var primes PrimeGenerator
	primes.current = 1
	primes.sieve = make(map[int][]int)
	return &primes
}

// Next advancaes the state of the PrimeGenerator and returns the next prime.
func (gen *PrimeGenerator) Next() int {
	for {
		gen.current = gen.current + 1
		existingFactors := gen.sieve[gen.current]
		if len(existingFactors) == 0 {
			gen.sieve[gen.current*gen.current] = append(existingFactors, gen.current)
			return gen.current
		}
		delete(gen.sieve, gen.current)
		for _, factor := range existingFactors {
			compositeFactors := gen.sieve[gen.current+factor]
			gen.sieve[gen.current+factor] = append(compositeFactors, factor)
		}

	}
}
