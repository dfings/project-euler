module Memoize

#nowarn "40"
let memoizeWithCacheKey cacheKeyFunc func =
  let cache = new System.Collections.Generic.Dictionary<_, _>()
  fun x ->
    let cacheKey = cacheKeyFunc x
    let ok, res = cache.TryGetValue(cacheKey)
    if ok then res
    else 
      let res = func x
      cache.[cacheKey] <- res
      res
