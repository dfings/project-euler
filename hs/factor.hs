module Factor (factor) where

import Primes

factor num = factor' num primes
  where factor' 1 _ = []
        factor' x p = if (mod x current_prime) == 0 
          then current_prime : factor' (div x current_prime) p
          else factor' x (tail p)
          where current_prime = head p
