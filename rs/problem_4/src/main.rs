fn products_of_three_digit_numbers() -> impl Iterator<Item = u32> {
    (100..1000).flat_map(|i| (i..1000).map(move |j| i * j))
}

fn is_palindrome(value: &u32) -> bool {
    let value = value.to_string();
    let rev: String = value.chars().rev().collect();
    value == rev
}

fn main() {
    let max = products_of_three_digit_numbers()
        .filter(is_palindrome)
        .max()
        .unwrap();
    println!("{}", max);
}
