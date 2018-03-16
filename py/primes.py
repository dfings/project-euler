def primes():
  i = 2
  sieve = {}

  while True:
    res = sieve.get(i)
    if not res:
      yield i
      sieve[i * i] = [i]
    else:
      del sieve[i]
      for j in res:
        sieve.setdefault(i + j, []).append(j)      
    i += 1


def primes2():
  i = 2
  seen = []

  def divisible_by_seen(x):
    for prime in seen:
      if x % prime == 0:
        return True
      elif prime * prime > x:
        return False
    return False

  while True:
    if not divisible_by_seen(i):
      seen.append(i)
      yield i
    i += 1