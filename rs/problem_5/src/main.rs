fn gcd(mut x: u32, mut y: u32) -> u32 {
    while y > 0 {
        let tmp = x;
        x = y;
        y = tmp % y;
    }
    x
}

fn lcm(x: u32, y: u32) -> u32 {
    (x * y) / gcd(x, y)
}

fn main() {
    println!("{}", (2..20).reduce(lcm).unwrap());
}
