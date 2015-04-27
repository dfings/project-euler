triple = head [[a, b, getC a b] |
               a <- [1..333],
               b <- [a..500], 
               a * a + b * b == (getC a b) * (getC a b)]
  where getC a b = 1000 - a - b

main = do 
  print $ triple
  print $ foldl1 (*) triple
