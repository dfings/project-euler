import qualified Data.List as List

productsOfTwo3DigitNumbers =
  reverse $ List.sort $ allProductsForStart start start
  where start = 999
        allProductsForStart x y = 
          if x == 0 then []
          else if y < 100 then allProductsForStart (x - 1) (x - 1)
          else (x * y) : allProductsForStart x (y - 1)

findFirstPalindrome candidates =
  if show num == reverse (show num) then num
  else findFirstPalindrome (tail candidates)
  where num = head candidates

main = print $ findFirstPalindrome $ productsOfTwo3DigitNumbers
