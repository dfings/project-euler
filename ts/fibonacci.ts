export function* fibonacci() {
  let a = 1n,
    b = 1n;
  while (true) {
    yield a;
    b = a + b;
    a = b - a;
  }
}
