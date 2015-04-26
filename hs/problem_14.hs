import qualified Data.Map.Strict as Map

getNext :: Int -> Int
getNext n = if mod n 2 == 0 then div n 2 else 3 * n + 1

collatzLength :: Int -> Int
collatzLength n = if n == 1 then 1 else 1 + collatzLength (getNext n)

bruteForceFindMaxLength :: Int -> (Int, Int)
bruteForceFindMaxLength n = maximum $ zip (map collatzLength [1..n]) [1..n]

cachedCollatzLength :: Int -> Map.Map Int Int -> (Int, Map.Map Int Int)
cachedCollatzLength n cache =
  if n == 1 then (1, cache)
  else case Map.lookup n cache of
    Just len -> (len, cache)
    Nothing -> (1 + len', cache'')
    where next = getNext n
          (len', cache') = cachedCollatzLength next cache
          cache'' = Map.insert n (1 + len') cache'

cachedFindMaxLength' :: Int -> Int -> Int -> Int -> Map.Map Int Int -> (Int, Int)
cachedFindMaxLength' i limit maxN maxLen cache =
  if i <= limit then cachedFindMaxLength' (i + 1) limit maxN' maxLen' cache'
  else (maxLen, maxN)
  where (len, cache') = cachedCollatzLength i cache
        maxN' = if len > maxLen then i else maxN
        maxLen' = max len maxLen

cachedFindMaxLength :: Int -> (Int, Int)
cachedFindMaxLength n = cachedFindMaxLength' 1 n 0 0 Map.empty

memoizedCollatzLength :: Int -> Int
memoizedCollatzLength n = if n == 1 then 1 else 1 + collatzLengthList !! nextIndex
  where nextIndex = (getNext n) - 1

collatzLengthList :: [Int]
collatzLengthList = map memoizedCollatzLength [1..]

memoizedFindMaxLength :: Int -> (Int, Int)
memoizedFindMaxLength n = maximum $ zip collatzLengthList [1..n]

main = do
  let maxN = 1000000
  -- Fast to slow. The infinite list memoized version takes O(N) time to index into.
  print $ cachedFindMaxLength maxN
  -- print $ bruteForceFindMaxLength maxN
  -- print $ memoizedFindMaxLength maxN