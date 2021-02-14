open Fibonacci

let is_even i = i % 2 = 0 
let in_bound i = i < 4000000

printfn "%d" (fibonacci |> Seq.takeWhile in_bound |> Seq.filter is_even |> Seq.sum)
