import gleam/int
import gleam/pair.{first, second}
import gleam/string
import gleam/yielder.{type Yielder, filter, map, range}
import lib.{cartesian_product, max, println}

pub fn main() {
  println(products_of_all_3_digit_numbers() |> filter(is_palindrome) |> max())
}

fn products_of_all_3_digit_numbers() -> Yielder(Int) {
  cartesian_product(range(from: 100, to: 999), range(from: 100, to: 999))
  |> map(fn(p) { first(p) * second(p) })
}

fn is_palindrome(value: Int) -> Bool {
  let s = int.to_string(value)
  s == string.reverse(s)
}
