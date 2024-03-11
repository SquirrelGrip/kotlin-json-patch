package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.JsonElement

/** A JSON patch processor that does nothing, intended for testing and validation.  */
class NoopProcessor : JsonPatchApplyProcessor() {
    companion object {
        val INSTANCE: NoopProcessor = NoopProcessor()
    }
}

class JsonPatchEditingContextTestImpl(var source: JsonElement): JsonPatchEditingContext {
    override fun remove(path: List<String>) {}
    override fun replace(path: List<String>, value: JsonElement) {}
    override fun add(path: List<String>, value: JsonElement) {}
    override fun move(fromPath: List<String>, toPath: List<String>) {}
    override fun copy(fromPath: List<String>, toPath: List<String>) {}
    override fun test(path: List<String>, value: JsonElement) {}
}