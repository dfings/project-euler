from primes import primes

def prime_factors(n):
  factors = []
  for prime in primes():
    if n % prime == 0:
      factors.append(n)
      while n % prime == 0:
        n /= prime
    if n == 1:
      return factors
