#nowarn "40"

let numColors = 7
let numPerColor = 10
let numToPick = 20
let sumWhenDone = numColors * numPerColor - numToPick

(* Counts the number of distinct colors that have been picked from the urn. *)
let countColorsPicked = List.filter ((<>) numPerColor) >> List.length

(* Generates one new urn state for each possible ball that could be picked. *)
let rec childGenerator head ballCount tail =
  let remaining() = childGenerator (head @ [ballCount]) (List.head tail) (List.tail tail)
  let oneChildStatePerBall = List.replicate ballCount
  let allChildStatesForColor = 
      if ballCount = 0 then [] 
      elif tail = [] then oneChildStatePerBall (head @ [ballCount - 1])
      else oneChildStatePerBall (head @ [ballCount - 1] @ tail)
  if tail = [] then allChildStatesForColor else allChildStatesForColor @ remaining()

(* Helper for using childGenerator on an urn. *)
let getChildren urn = childGenerator [] (List.head urn) (List.tail urn)

(* Tuple support. *)
let sumTuple (a1, a2) (b1, b2) = a1 + b1, a2 + b2
let bigIntTuple (a1:int, a2:int) = (bigint a1, bigint a2)
let sumBigIntTuples = List.fold sumTuple (bigIntTuple (0, 0))

(* Computes (totalColorsPicked, totalLeaves) for leaves rooted at this subtree. *)
let rec computeCounts = Memoize.memoizeWithCacheKey List.sort (fun urn ->
  (* If this is a leaf, then we can just count the colors directly. *)
  if List.sum urn = sumWhenDone then bigIntTuple ((countColorsPicked urn), 1)
  (* Otherwise we need to sum up the values of all leaves rooted at this subtree. *)  
  else getChildren urn |> List.map computeCounts |> sumBigIntTuples  
)

let startingUrn = List.replicate numColors numPerColor 
let totalColorsPicked, totalLeaves = computeCounts startingUrn
printfn "Total colors = %A" totalColorsPicked
printfn "Total leaves = %A" totalLeaves
printfn "Average = %0.9f" (float totalColorsPicked / float totalLeaves)
