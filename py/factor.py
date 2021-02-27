from math import sqrt

def prime_factors(n):
  factors = []
  i = 2
  while n > 1:
    if n % i == 0:
      factors.append(i)
      n /= i
    else:
      i += 1
  return factors


def factor(n):
  factors = []
  i = 2
  limit = sqrt(n)
  while i < limit:
    if n % i == 0:
      factors.append(i)
      factors.append(n / i)
    i += 1
  if i * i == n:
    factors.append(i)
  return factors
