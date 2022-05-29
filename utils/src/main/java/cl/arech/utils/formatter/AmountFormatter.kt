package cl.arech.utils.formatter

import java.text.DecimalFormat

fun Double.toAmountFormat(): String {
    val dec = DecimalFormat("#,###.##")
    return "$${dec.format(this)}"
}