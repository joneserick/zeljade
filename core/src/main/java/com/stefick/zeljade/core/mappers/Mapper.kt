package com.stefick.zeljade.core.mappers

fun interface Mapper<T, E> {
    fun toDomain(dto: T?): E?

}