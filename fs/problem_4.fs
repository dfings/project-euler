let multiplyByAll3DigitNumbers x = List.map ((*) x) [100..999]
let productsOfTwo3DigitNumbers = 
  [100..999] |> List.map multiplyByAll3DigitNumbers |> List.concat |> List.sort |> List.rev

let reverseString (s:string) = new string(s.Reverse())
let isPalindrome x = 
  let s = x.ToString()
  s = reverseString s

printfn "%d" (productsOfTwo3DigitNumbers |> List.filter isPalindrome |> List.head)