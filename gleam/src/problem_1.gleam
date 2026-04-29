import gleam/int
import gleam/io
import gleam/yielder.{filter, fold, range}

pub fn main() {
  let total = range(from: 1, to: 1000) |> filter(sieve) |> fold(0, int.add)
  io.println(int.to_string(total))
}

fn sieve(i: Int) -> Bool {
  i % 3 == 0 || i % 5 == 0
}
