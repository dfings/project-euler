fn square(x: i32) -> i32 {
    x * x
}

fn square_of_sum(x: i32) -> i32 {
    square((1..x).sum())
}

fn sum_of_squares(x: i32) -> i32 {
    (1..x).map(square).sum()
}

fn main() {
    println!("{}", square_of_sum(100) - sum_of_squares(100));
}
