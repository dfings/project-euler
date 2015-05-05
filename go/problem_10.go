package main

import "fmt"

func main() {
  gen := NewPrimeGenerator()
  sum := 0
  for {
    prime := gen.Next()
    if prime > 2000000 {
      break
    }
    sum += prime
  }
  fmt.Printf("%d\n", sum)
}
