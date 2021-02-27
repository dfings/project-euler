module Fibonacci where

fibonacci :: [Integer]
fibonacci =
  fibonacci' 0 1
  where
    fibonacci' :: Integer -> Integer -> [Integer]
    fibonacci' a b = a : fibonacci' b (a + b)
