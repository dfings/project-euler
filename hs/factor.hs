module Factor where

factor num = factor' num 2
  where factor' 1 _ = []
        factor' x i 
          | (mod x i) == 0 = i : factor' (div x i) i 
          | otherwise = factor' x (i + 1)
