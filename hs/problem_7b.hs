-- Ideally we'd use Persistent vectors here, much more efficient.
-- But that's not in the core library.
import qualified Data.Vector as Vector

isDivisibleByAny i ps =
  if Vector.null ps then False
  else if mod i p == 0 then True
  else if p * p > i then False
  else isDivisibleByAny i (Vector.tail ps)
  where p = Vector.head ps

getNextPrime i ps =
  if not (isDivisibleByAny i ps) then i
  else getNextPrime (i + 1) ps

primeGenerator allPrior currentPrime =
  currentPrime : primeGenerator newPrior (getNextPrime (currentPrime + 1) newPrior)
  where newPrior = Vector.snoc allPrior currentPrime

primes = primeGenerator Vector.empty 2

main = print $ primes !! 10000
