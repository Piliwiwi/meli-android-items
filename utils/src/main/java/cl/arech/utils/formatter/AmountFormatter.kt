package cl.arech.utils.formatter

fun Int.toAmountFormat(): String {
    val stringAmount = toString()
    return "$${
        when {
            stringAmount.length <= 3 -> stringAmount
            stringAmount.length % 3 == 0 -> stringAmount
                .chunked(3)
                .joinToString(".")
            else -> stringAmount
                .reversed()
                .chunked(3)
                .joinToString(".")
                .reversed()
        }
    }"
}