import qualified Data.List as List
import qualified Data.Map.Strict as Map

numColors = 7

numPerColor = 10

numToPick = 20

-- Determines if we've picked all the balls we need to from an urn.
allBallsPicked = (==) (numColors * numPerColor - numToPick) . sum

-- Counts the number of distinct colors that have been picked from the urn.
countColorsPicked = toInteger . length . filter (numPerColor /=)

leaf urn = (countColorsPicked urn, 1)

getNextStates uhead ballCount utail
  | ballCount == 0 = []
  | null utail = oneChildStatePerBall (uhead ++ [ballCount - 1])
  | otherwise = oneChildStatePerBall (uhead ++ [ballCount - 1] ++ utail)
  where
    oneChildStatePerBall = replicate ballCount

-- Generates one new urn state for each possible ball that could be picked.
childGenerator uhead ballCount utail
  | null utail = allChildStatesForColor
  | otherwise = allChildStatesForColor ++ remaining
  where
    allChildStatesForColor = getNextStates uhead ballCount utail
    remaining = childGenerator (uhead ++ [ballCount]) (head utail) (tail utail)

-- Helper for using childGenerator on an urn.
getChildren urn = childGenerator [] (head urn) (tail urn)

-- Tuple support.
sumTuple (a1, a2) (b1, b2) = (a1 + b1, a2 + b2)

-- Handles creating and caching intermediate results.
makeResult urn totals cache =
  (totals, Map.insert (List.sort urn) totals cache)

-- Computes (totalColorsPicked, totalLeaves) for leaves rooted at this subtree.
computeCounts urn = totals
  where
    (totals, _) = computeCountsWithCache urn Map.empty

computeCountsWithCache urn cache =
  case Map.lookup (List.sort urn) cache of
    Just totals -> (totals, cache)
    Nothing ->
      if allBallsPicked urn
        then makeResult urn (leaf urn) cache
        else makeResult urn totals newCache
      where
        children = getChildren urn
        (totals, newCache) = mergeChildResults urn cache children

mergeChildResults urn cache (child : rest)
  | null rest = (childTotals, childCache)
  | otherwise = (sumTuple childTotals restTotals, restCache)
  where
    (childTotals, childCache) = computeCountsWithCache child cache
    (restTotals, restCache) = mergeChildResults urn childCache rest

main = do
  let startingUrn = replicate numColors numPerColor
  let (totalColorsPicked, totalLeaves) = computeCounts startingUrn
  putStrLn $ "Total colors = " ++ show totalColorsPicked
  putStrLn $ "Total leaves = " ++ show totalLeaves
  putStrLn $ "Average = " ++ show (fromIntegral totalColorsPicked / fromIntegral totalLeaves)
