package main

import "fmt"

func divisibleThrough20(n int) bool {
  for i := 2; i <= 20; i++ {
    if n % i != 0 {
      return false
    }
  } 
  return true
}

func main() {
  n := 2520
  for {
    if divisibleThrough20(n) {
      break
    }
    n += 1
  }
  fmt.Printf("%d\n", n)
}
