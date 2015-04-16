let sieve i = i % 3 = 0 || i % 5 = 0
printfn "%d" (List.filter sieve [1..1000] |> List.sum)
