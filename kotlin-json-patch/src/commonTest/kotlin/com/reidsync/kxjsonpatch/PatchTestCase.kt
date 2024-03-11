package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.*

class PatchTestCase(
    val isOperation: Boolean,
    val node: JsonObject
) {
    companion object {
        private val MAPPER = GsonObjectMapper()

        fun load(testData: String): Collection<PatchTestCase> {
            val tree: JsonElement = MAPPER.readTree(testData)
            val result: MutableList<PatchTestCase> = ArrayList()
            for (node in tree.jsonObject["errors"]!!.jsonArray) {
                if (isEnabled(node)) {
                    result.add(PatchTestCase(false, node.jsonObject))
                }
            }
            for (node in tree.jsonObject.get("ops")!!.jsonArray) {
                if (isEnabled(node)) {
                    result.add(PatchTestCase(true, node.jsonObject))
                }
            }
            return result
        }

        private fun isEnabled(node: JsonElement): Boolean {
            val disabled: JsonElement? = node.jsonObject["disabled"]
            return (disabled == null || !disabled.jsonPrimitive.boolean)
        }
    }
}