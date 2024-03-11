package com.reidsync.kxjsonpatch

enum class Operation(
    val value: String
) {
    ADD("add"),
    REMOVE("remove"),
    REPLACE("replace"),
    MOVE("move"),
    COPY("copy"),
    TEST("test"),
}

internal object Operations {
    private val OPS = Operation.entries.associate {
        it.value to it
    }
    private val NAMES = Operation.entries.associate {
        it.ordinal to it
    }

    fun opFromName(rfcName: String): Operation =
        OPS[rfcName.lowercase()] ?: throw InvalidJsonPatchException("unknown / unsupported operation $rfcName")
}
