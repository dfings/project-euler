module Primes where

import qualified Data.IntMap.Strict as Map

type Sieve = Map.IntMap [Int]
type SieveAndPrime = (Sieve, Int)

primes :: [Int]
primes = primes' (nextPrime (Map.empty, 2))
  where primes' :: SieveAndPrime -> [Int]
        primes' (sieve, currentPrime) = currentPrime : primes' (nextPrime (sieve, currentPrime + 1))
        nextPrime :: SieveAndPrime -> SieveAndPrime
        nextPrime (sieve, i) =
          case Map.lookup i sieve of
            Nothing -> (Map.insert (i * i) [i] sieve, i)
            Just existingFactors -> nextPrime ((addToSieve sieve i existingFactors), (i + 1))
        addToSieve :: Sieve -> Int -> [Int] -> Sieve
        addToSieve sieve _ [] = sieve
        addToSieve sieve nonPrime (p:ps) = addToSieve sieve'' nonPrime ps
          where composite = nonPrime + p
                existingFactors = Map.findWithDefault [] composite sieve
                sieve' = Map.insert composite (p : existingFactors) sieve
                sieve'' = Map.delete nonPrime sieve'