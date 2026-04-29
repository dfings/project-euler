import gleam/int
import gleam/io
import gleam/pair.{first, second}
import gleam/yielder.{type Yielder, fold, iterate, map}

pub fn fibonacci() -> Yielder(Int) {
  iterate(#(1, 1), fn(p) { #(second(p), first(p) + second(p)) }) |> map(first)
}

pub fn println(value: Int) {
  io.println(int.to_string(value))
}

pub fn sum(in yielder: Yielder(Int)) -> Int {
  yielder |> fold(0, int.add)
}
