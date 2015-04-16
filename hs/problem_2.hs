gen_fib a b = 
  if b >= 4000000 then []
  else b : gen_fib b (a + b)

main = print $ sum $ filter even (gen_fib 1 1)
