extern crate itertools;

use itertools::iproduct;

fn products_of_three_digit_numbers() -> impl Iterator<Item = u32> {
    iproduct!(100..1000, 100..1000).map(|(a, b)| a * b)
}

fn is_palindrome(value: &u32) -> bool {
    let value_str = value.to_string();
    value_str == value_str.chars().rev().collect::<String>()
}

fn main() {
    if let Some(max) = products_of_three_digit_numbers().filter(is_palindrome).max() {
        println!("{}", max);
    }
}
