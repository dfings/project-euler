isNotDivisibleBy _ [] = True
isNotDivisibleBy i primes =
  if mod i firstPrime == 0 then False
  else if firstPrime * firstPrime > i then True
  else isNotDivisibleBy i (tail primes)
  where firstPrime = head primes

appendIfPrime i primes =
  if isNotDivisibleBy i primes then primes ++ [i]
  else primes

getNthPrime n =
  loop 2 []
  where loop i primes = if length primes == n then i - 1 else loop (i + 1) (appendIfPrime i primes)

main = print $ getNthPrime 10001
