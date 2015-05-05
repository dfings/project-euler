package main

import "fmt"

func main() {
  sum := 0
  for i := 1; true; i++ {
    sum += i
    if len(Factors(sum)) > 500 {
    	fmt.Printf("%d\n", sum)
    	break
    }
  }
}
