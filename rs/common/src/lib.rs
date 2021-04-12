pub fn fibonacci() -> impl Iterator<Item = u64> {
    struct State {
        a: u64,
        b: u64,
    }
    impl Iterator for State {
        type Item = u64;
        fn next(&mut self) -> Option<u64> {
            let out = self.a;
            self.a = self.b;
            self.b += out;
            Some(out)
        }
    }
    State { a: 0, b: 1 }
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
