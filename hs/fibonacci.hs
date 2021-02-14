module Fibonacci where

fibonacci =
  gen 0 1
  where gen a b = a : gen b (a + b)
