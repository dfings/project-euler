import gleam/int.{modulo, range}
import gleam/io

pub fn main() {
  let total =
    range(from: 1, to: 1000, with: 0, run: fn(acc, i) {
      case modulo(i, 3) == Ok(0) || modulo(i, 5) == Ok(0) {
        True -> i + acc
        False -> acc
      }
    })
  io.println(int.to_string(total))
}
