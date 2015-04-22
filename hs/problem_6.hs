square x = x * x
squareOfSum x = square $ sum [1..x]
sumOfSquare x = sum $ map square [1..x]

main = print $ squareOfSum 100 - sumOfSquare 100