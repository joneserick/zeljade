package com.stefick.zeljade.custom.shared.extensions

fun String.capitalizeWords(delimiter: String = " ", separator: String = " "): String {
    return this.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}