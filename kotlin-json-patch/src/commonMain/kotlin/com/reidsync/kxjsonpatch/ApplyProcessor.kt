package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.*

class ApplyProcessor(private val target: JsonElement) : JsonPatchApplyProcessor(target.deepCopy()) {
    fun result(): JsonElement = targetSource
}

