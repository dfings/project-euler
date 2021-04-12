extern crate common;

use common::fibonacci;

fn main() {
    let sum: u64 = fibonacci()
        .take_while(|i| i < &4000000u64)
        .filter(|i| i % 2 == 0)
        .sum();
    println!("{}", sum);
}
