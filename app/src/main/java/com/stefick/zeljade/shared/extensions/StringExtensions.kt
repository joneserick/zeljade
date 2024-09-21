package com.stefick.zeljade.shared.extensions

fun String.capitalizeWords(delimiter: String = " ", separator: String = " "): String {
    return this.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}