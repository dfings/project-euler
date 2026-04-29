import gleam/yielder.{filter, take_while}
import lib.{fibonacci, println, sum}

pub fn main() {
  let total =
    fibonacci()
    |> take_while(fn(i) { i < 4_000_000 })
    |> filter(is_even)
    |> sum()
  println(total)
}

fn is_even(n: Int) -> Bool {
  n % 2 == 0
}
