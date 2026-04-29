import gleam/yielder.{filter, range}
import lib.{println, sum}

pub fn main() {
  let total = range(from: 1, to: 1000) |> filter(sieve) |> sum()
  println(total)
}

fn sieve(i: Int) -> Bool {
  i % 3 == 0 || i % 5 == 0
}
