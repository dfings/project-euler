(*
  $ fsharpc memoize.fs problem_493.fs
  $ mono problem_493.exe
*)

#nowarn "40"

let numColors = 7
let numPerColor = 10
let numToPick = 20

(* Determines if we've picked all the balls we need to from an urn. *)
let allBallsPicked = Seq.sum >> (=) (numColors * numPerColor - numToPick)

(* Counts the number of distinct colors that have been picked from the urn. *)
let countColorsPicked = Seq.filter ((<>) numPerColor) >> Seq.length

(* Returns a new urn with a ball of the given color removed. *)
let pick color urn =
  urn 
  |> List.indexed 
  |> List.map(fun (i, v) -> if (i = color) then v - 1 else v)

(* Tuple support. *)
let sumTuple (a1, a2) (b1, b2) = a1 + b1, a2 + b2
let bigIntTuple (a1:int, a2:int) = (bigint a1, bigint a2)
let sumBigIntTuples = Seq.reduce sumTuple

(* Computes (totalColorsPicked, totalLeaves) for leaves rooted at this subtree. *)
let rec computeCounts = Memoize.memoizeWithCacheKey List.sort (fun urn ->
  (* If this is a leaf, then we can just count the colors directly. *)
  if allBallsPicked urn then bigIntTuple ((countColorsPicked urn), 1)
  (* Otherwise we need to sum up the values of all leaves rooted at this subtree. *)  
  else 
    [0..numColors-1] 
    |> Seq.map(fun color -> 
      [1..(List.item color urn)] 
      |> Seq.map(fun _ -> computeCounts (pick color urn)))
    |> Seq.concat
    |> sumBigIntTuples
)

let startingUrn = List.replicate numColors numPerColor 
let totalColorsPicked, totalLeaves = computeCounts startingUrn
printfn "Total colors = %A" totalColorsPicked
printfn "Total leaves = %A" totalLeaves
printfn "Average = %0.9f" (float totalColorsPicked / float totalLeaves)
