extern crate common;

use common::primes;

fn main() {
    println!("{}", primes().skip(10000).next().unwrap());
}
