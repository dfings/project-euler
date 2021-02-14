let factor num = 
  let rec factor' num i =
    if num = 1L then []
    elif num % i = 0L then i :: factor' (num / i) i
    else factor' num (i + 1L)
  factor' num 2L

printfn "%d" (factor 600851475143L |> List.rev |> List.head)
