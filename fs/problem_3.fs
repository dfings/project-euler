let rec factorWith num i =
  if num = 1L then []
  elif num % i = 0L then i :: factorWith (num / i) i
  else factorWith num (i + 1L)

let factor num = factorWith num 2L

printfn "%d" (factor 600851475143L |> List.rev |> List.head)
