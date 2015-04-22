isNotDivisibleBy _ [] = True
isNotDivisibleBy i (p:ps) =
  if mod i p == 0 then False
  else if p * p > i then True
  else isNotDivisibleBy i ps

getNextPrime ps = getNextPrimeFrom (1 + last ps) ps
  where getNextPrimeFrom i ps = if isNotDivisibleBy i ps 
        then i 
        else getNextPrimeFrom (i + 1) ps

getNthPrime 1 = 2
getNthPrime n = getNextPrime (take (n - 1) primes)

primes = map getNthPrime [1..]

main = print $ getNthPrime 10001
