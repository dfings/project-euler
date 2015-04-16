let rec gen_fib a b = 
  if b >= 4000000 then []
  else b :: gen_fib b (a + b)

let fib_seq = gen_fib 1 1
let is_even i = i % 2 = 0 

printfn "%d" (fib_seq |> List.filter is_even |> List.sum)
