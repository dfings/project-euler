module Primes where

import qualified Data.Map.Strict as Map

addToSieve sieve _ [] = sieve
addToSieve sieve nonPrime (p:ps) =
  addToSieve sieve'' nonPrime ps
  where composite = nonPrime + p
        existingFactors = Map.findWithDefault [] composite sieve
        sieve' = Map.insert composite (p : existingFactors) sieve
        sieve'' = Map.delete nonPrime sieve'

nextPrime (sieve, i) =
  case Map.lookup i sieve of
    Nothing -> (Map.insert (i * i) [i] sieve, i)
    Just existingFactors -> nextPrime ((addToSieve sieve i existingFactors), (i + 1))

primes = gen (nextPrime (Map.empty, 2))
  where gen (sieve, currentPrime) = currentPrime : gen (nextPrime (sieve, currentPrime + 1))
