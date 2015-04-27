let productsOfTwo3DigitNumbers = [for x in 100..999 do for y in 100..999 -> x * y]

let reverseString (s:string) = new string(Array.rev (s.ToCharArray()))
let isPalindrome x = 
  let s = x.ToString()
  s = reverseString s

printfn "%d" (productsOfTwo3DigitNumbers |> List.filter isPalindrome |> List.max)