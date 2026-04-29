import gleam/int
import gleam/io
import gleam/pair.{first, second}
import gleam/yielder.{type Yielder, filter, fold, iterate, map, take_while}

pub fn main() {
  let total =
    fibonacci()
    |> take_while(fn(i) { i < 4_000_000 })
    |> filter(is_even)
    |> fold(0, int.add)
  io.println(int.to_string(total))
}

fn fibonacci() -> Yielder(Int) {
  iterate(#(1, 1), fn(p) { #(second(p), first(p) + second(p)) }) |> map(first)
}

fn is_even(n: Int) -> Bool {
  n % 2 == 0
}
