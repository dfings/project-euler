import gleam/int
import gleam/io

pub fn main() {
  let total =
    int.range(from: 1, to: 1000, with: 0, run: fn(acc, i) {
      case i % 3 == 0 || i % 5 == 0 {
        True -> i + acc
        False -> acc
      }
    })
  io.println(int.to_string(total))
}
