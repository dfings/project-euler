import java.math.BigInteger

fun factorial(n: Int): BigInteger =
    (1..n).map { it.toBigInteger() }.reduce(BigInteger::multiply)