package main

import "fmt"

func lcm(x int, y int) int {
  lcm := 1
  factor := 2
  for x != 1 || y != 1 {
    if x % factor == 0 || y % factor == 0 {
      lcm *= factor
      if x % factor == 0 {
        x /= factor
      } 
      if y % factor == 0 {
        y /= factor
      }
    } else {
      factor += 1
    }
  }
  return lcm
}

func main() {
  n := 1
  for i := 2; i <= 20 ; i++ {
    n = lcm(n, i)
  }
  fmt.Printf("%d\n", n)
}
