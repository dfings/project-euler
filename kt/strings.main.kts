fun String.toDigits() = map(Character::getNumericValue).map { it.toLong() }
fun String.productOfDigits() = toDigits().reduce(Long::times)
fun String.sumOfDigits() = toDigits().sum()
