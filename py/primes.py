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
