extern crate itertools;

use itertools::unfold;

fn fibonacci() -> impl Iterator<Item = u32> {
    unfold((0, 1), |(a, b)| {
        *b = *a + *b;
        *a = *b - *a;
        Some(*a)
    })
}

fn main() {
    let sum: u32 = fibonacci()
        .take_while(|i| i < &4000000u32)
        .filter(|i| i % 2 == 0)
        .sum();
    println!("{}", sum);
}
