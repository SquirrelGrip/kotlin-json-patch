package com.reidsync.kxjsonpatch.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

class GsonObjectMapper {
    fun readTree(jsondata: String?): JsonElement =
        if (jsondata != null) {
            Json.parseToJsonElement(jsondata)
        } else {
            JsonNull
        }
}