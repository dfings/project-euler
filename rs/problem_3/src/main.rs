extern crate common;

use common::prime_factors;

fn main() {
    println!("{}", prime_factors(600851475143).last().unwrap());
}
