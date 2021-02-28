import Fibonacci -- fibonacci.hs

main = print $ sum $ filter even $ takeWhile (< 4000000) fibonacci
