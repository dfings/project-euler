extern crate itertools;

use itertools::unfold;

pub fn fibonacci() -> impl Iterator<Item = u64> {
    unfold((0, 1), |(a, b)| {
        *b = *a + *b;
        *a = *b - *a;
        Some(*a)
    })
}

pub fn prime_factors(mut n: u64) -> Vec<u64> {
    let mut factors = Vec::new();
    let mut i = 2;
    while n > 1 {
        if n % i == 0 {
            factors.push(i);
            n /= i;
        } else {
            i += 1;
        }
    }
    factors
}
