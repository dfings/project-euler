sieve i = mod i 3 == 0 || mod i 5 == 0

main = print $ sum $ filter sieve [1 .. 999]
