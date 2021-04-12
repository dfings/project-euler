extern crate itertools;

use itertools::unfold;

pub fn fibonacci() -> impl Iterator<Item = u64> {
    unfold((0, 1), |(a, b)| {
        *b = *a + *b;
        *a = *b - *a;
        Some(*a)
    })
}
