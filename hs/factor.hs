module Factor where

factor :: Integer -> [Integer]
factor num = factor' num 2
  where factor' :: Integer -> Integer -> [Integer]
        factor' x i 
          | x == 1 = []
          | (mod x i) == 0 = i : factor' (div x i) i 
          | otherwise = factor' x (i + 1)
