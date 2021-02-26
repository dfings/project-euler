fun <T, U> ((T) -> U).memoize(): (T) -> U {
  val cache = hashMapOf<T, U>()
  return { x -> cache.getOrPut(x) { this(x) } }
}
