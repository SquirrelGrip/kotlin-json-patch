package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.*

internal object NodeType {
    const val ARRAY = 1
    const val OBJECT = 2
    const val PRIMITIVE_OR_NULL = 3

    fun getNodeType(node: JsonElement): Int =
        when (node) {
            is JsonArray -> ARRAY
            is JsonObject -> OBJECT
            else -> PRIMITIVE_OR_NULL
        }
}
