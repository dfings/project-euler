import qualified Data.List as List
import qualified Data.Map.Strict as Map  

numColors = 7
numPerColor = 10
numToPick = 2

-- Determines if we've picked all the balls we need to from an urn.
allBallsPicked = (==) (numColors * numPerColor - numToPick) . sum

-- Counts the number of distinct colors that have been picked from the urn.
countColorsPicked = (length . filter ((/=) numPerColor))

getNextStates uhead ballCount utail =
  if ballCount == 0 then [] 
  else if utail == [] then oneChildStatePerBall (uhead ++ [ballCount - 1])
  else oneChildStatePerBall (uhead ++ [ballCount - 1] ++ utail)
  where oneChildStatePerBall = replicate ballCount


-- Generates one new urn state for each possible ball that could be picked.
childGenerator uhead ballCount utail =
  if utail == [] then allChildStatesForColor else allChildStatesForColor ++ remaining
  where allChildStatesForColor = getNextStates uhead ballCount utail
        remaining = childGenerator (uhead ++ [ballCount]) (head utail) (tail utail)        

-- Helper for using childGenerator on an urn.
getChildren urn = childGenerator [] (head urn) (tail urn)

-- Tuple support.
sumTuple (a1, a2) (b1, b2) = (a1 + b1, a2 + b2)
sumTuples = foldl1 sumTuple

makeResult urn totals cache =
  (totals, Map.insert (List.sort urn) totals cache)

-- Computes (totalColorsPicked, totalLeaves) for leaves rooted at this subtree.
computeCounts urn = totals
   where (totals, _) = computeCountsWithCache urn Map.empty

computeCountsWithCache urn cache = 
  if allBallsPicked urn then singleResult urn cache
  else multiResult urn cache

singleResult urn cache = makeResult urn ((countColorsPicked urn), 1) cache
multiResult urn cache =
  makeResult urn totals newCache
  where children = getChildren urn
        (totals, newCache) = mergeChildResults urn cache children

mergeChildResults urn cache (child:rest) = 
  if rest == [] then (childTotals, childCache)
  else (sumTuple childTotals restTotals, restCache)
  where (childTotals, childCache) = computeCountsWithCache child cache
        (restTotals, restCache) = mergeChildResults urn childCache rest

startingUrn = replicate numColors numPerColor 

main = do 
  --print $ computeCounts startingUrn
  let ((totalColorsPicked, totalLeaves), cache) = computeCountsWithCache startingUrn Map.empty
  putStrLn $ "Total colors = " ++ show totalColorsPicked
  putStrLn $ "Total leaves = " ++ show totalLeaves
  putStrLn $ "Average = " ++ show ((fromIntegral totalColorsPicked) / (fromIntegral totalLeaves))
  print cache

