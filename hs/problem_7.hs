isNotDivisibleBy _ [] = True
isNotDivisibleBy i ps =
  if mod i firstPrime == 0 then False
  else if firstPrime * firstPrime > i then True
  else isNotDivisibleBy i (tail ps)
  where firstPrime = head ps

getNextPrime ps = getNextPrimeFrom (1 + last ps) ps
  where getNextPrimeFrom i ps = if isNotDivisibleBy i ps 
        then i 
        else getNextPrimeFrom (i + 1) ps

getNthPrime 1 = 2
getNthPrime n = getNextPrime (take (n - 1) primes)

primes = map getNthPrime [1..]

main = print $ getNthPrime 10001
