package com.stefick.zeljade.core.enums

enum class CategoryEnum(name: String) {
    CREATURES("creatures"),
    EQUIPMENT("equipment"),
    MONSTERS("monsters"),
    TREASURES("treasures"),
    MATERIALS("materials"),
    ENTRY("entry");

    companion object {
        fun fromValueOrDefault(value: String, default: CategoryEnum = ENTRY): CategoryEnum {
            return values().firstOrNull { value == it.name } ?: default
        }
    }

}