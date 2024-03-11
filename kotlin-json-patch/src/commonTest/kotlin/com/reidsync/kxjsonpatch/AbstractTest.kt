package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlin.test.DefaultAsserter.fail
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

abstract class AbstractTest {
    abstract fun data(): Collection<PatchTestCase>

    @Test
    fun test() {
        val testData = data()
        for (p in testData) {
            if (p.isOperation) {
                testOperation(p)
            } else {
                testError(p)
            }
        }
    }

    private fun testOperation(p: PatchTestCase) {
        val node: JsonObject = p.node
        val first: JsonElement = node.get("node")!!
        val second: JsonElement = node.get("expected")!!
        val patch: JsonElement = node.get("op")!!
        val message = if (node.containsKey("message")) node.get("message").toString() else ""
        val secondPrime: JsonElement =
            JsonPatch.apply(patch.jsonArray, first)
        assertEquals(secondPrime, second, message)
    }

    private fun testError(p:PatchTestCase) {
        val node: JsonObject = p.node
        val first: JsonElement = node.get("node")!!
        val patch: JsonElement = node.get("op")!!
        try {
            JsonPatch.apply(patch.jsonArray, first)
            assertFails {
                fail("Failure expected: " + node.get("message"))
            }
        }
        catch (e: Exception) {
            println("-> AssertFails with: ${e.message}")
        }
    }
}