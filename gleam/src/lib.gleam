import gleam/int
import gleam/io
import gleam/pair.{first, second}
import gleam/yielder.{type Yielder, fold, iterate, map}

pub fn fibonacci() -> Yielder(Int) {
  iterate(#(1, 1), fn(p) { #(second(p), first(p) + second(p)) }) |> map(first)
}

pub fn prime_factors(value: Int) -> List(Int) {
  prime_factor_loop(value, 2)
}

fn prime_factor_loop(n: Int, i: Int) -> List(Int) {
  case n {
    1 -> []
    n if n % i == 0 -> [n, ..prime_factor_loop(n / i, i)]
    _ -> prime_factor_loop(n, i + 1)
  }
}

pub fn println(value: Int) {
  io.println(int.to_string(value))
}

pub fn sum(in yielder: Yielder(Int)) -> Int {
  fold(yielder, 0, int.add)
}
