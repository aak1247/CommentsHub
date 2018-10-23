package com.aak1247.model

enum class State(val code: Number) {
    BLOCKED(-1),
    NORMAL(0),
    OUTDATED(1),
    DELETED(2)
}

enum class Gender(val code: Number) {
    UNKNOWN(0),
    MALE(1),
    FEMALE(2)
}

