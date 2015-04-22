isDivisibleByAny _ [] = False
isDivisibleByAny i (p:ps) =
  if mod i p == 0 then True
  else if p * p > i then False
  else isDivisibleByAny i ps

getNextPrime ps = getNextPrimeFrom (1 + last ps) ps
  where getNextPrimeFrom i ps = if not (isDivisibleByAny i ps)
        then i 
        else getNextPrimeFrom (i + 1) ps

getNthPrime 1 = 2
getNthPrime n = getNextPrime (take (n - 1) primes)

primes = map getNthPrime [1..]

main = print $ getNthPrime 10001
