package main

import "fmt"

func main() {
  for a := 1; a < 333; a++ {
    for b := a + 1; b < 500 - a; b++ {
      c := 1000 - a - b
      if a * a + b * b == c * c {
        fmt.Printf("%d %d %d -> %d\n", a, b, c, a * b * c)
        return
      }
    }
  }
}
