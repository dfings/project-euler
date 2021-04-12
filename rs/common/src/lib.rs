use std::collections::HashMap;

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

pub fn primes() -> impl Iterator<Item = u64> {
    struct State {
        current: u64,
        sieve: HashMap<u64, Vec<u64>>,
    }
    impl Iterator for State {
        type Item = u64;
        fn next(&mut self) -> Option<u64> {
            loop {
                self.current += 1;
                let existing_factors = self.sieve.remove(&self.current);
                if existing_factors.is_none() {
                    self.sieve
                        .insert(self.current * self.current, vec![self.current]);
                    return Some(self.current);
                }
                for factor in existing_factors.unwrap() {
                    self.sieve
                        .entry(self.current + factor)
                        .or_insert_with(Vec::new)
                        .push(factor);
                }
            }
        }
    }
    State {
        current: 1,
        sieve: HashMap::new(),
    }
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
