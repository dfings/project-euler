extern crate common;

use common::prime_factors;

fn main() {
    let factors = prime_factors(600851475143);
    if let Some(x) = factors.last() {
        println!("{}", x);
    }
}
