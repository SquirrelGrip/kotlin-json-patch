package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlin.jvm.JvmStatic

internal class Diff {
    val operation: Operation
    val path: MutableList<Any>
    val value: JsonElement
    val toPath: List<Any> //only to be used in move operation

    constructor(operation: Operation, path: List<Any>, value: JsonElement) {
        this.operation = operation
        this.path = path.toMutableList()
        this.toPath= listOf()
        this.value = value
    }

    constructor(operation: Operation, fromPath: List<Any>, toPath: List<Any>) {
        this.operation = operation
        this.path = fromPath.toMutableList()
        this.toPath = toPath
        this.value = JsonNull
    }

    companion object {
        @JvmStatic
        fun generateDiff(replace: Operation, path: List<Any>, target: JsonElement): Diff {
            return Diff(replace, path, target)
        }
    }
}
