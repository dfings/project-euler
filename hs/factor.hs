module Factor where

factor num = factor' num 2
  where factor' 1 _ = []
        factor' x i = if (mod x i) == 0 
          then i : factor' (div x i) i 
          else factor' x (i + 1)
