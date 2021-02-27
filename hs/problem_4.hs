productsOfTwo3DigitNumbers = [x * y | x <- [100 .. 999], y <- [100 .. 999]]

isPalindrome x = s == reverse s where s = show x

main = print $ maximum $ filter isPalindrome productsOfTwo3DigitNumbers
