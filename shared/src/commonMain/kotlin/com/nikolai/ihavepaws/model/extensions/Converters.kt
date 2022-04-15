package com.nikolai.ihavepaws.model.extensions

fun Long.toBoolean() = this > 0

fun Boolean.toLong() = when(this) {
    true -> 1L
    false -> 0L
}