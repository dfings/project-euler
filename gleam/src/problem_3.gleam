import gleam/list.{last}
import gleam/result.{unwrap}
import lib.{prime_factors, println}

pub fn main() {
  println(unwrap(last(prime_factors(600_851_475_143)), 0))
}
