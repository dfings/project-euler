import qualified Data.List as List

multiplyByAll3DigitNumbers x = map ((*) x) [100..999]

productsOfTwo3DigitNumbers =
  reverse $ List.sort $ concat $ map multiplyByAll3DigitNumbers [100..999]

isPalindrome x = s == reverse s where s = show x

main = print $ head $ filter isPalindrome productsOfTwo3DigitNumbers
