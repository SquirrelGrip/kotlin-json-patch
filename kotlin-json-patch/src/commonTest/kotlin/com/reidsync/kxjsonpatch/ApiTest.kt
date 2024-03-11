package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ApiTest {
    @Test
    fun applyingNonArrayPatchShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"not\": \"a patch\"}]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun applyingAnInvalidArrayShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[1, 2, 3, 4, 5]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun applyingAPatchWithAnInvalidOperationShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"op\": \"what\"}]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun validatingNonArrayPatchShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("{\"not\": \"a patch\"}")
            JsonPatch.validate(invalid)
        }
    }

    @Test
    fun validatingAnInvalidArrayShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[1, 2, 3, 4, 5]")
            JsonPatch.validate(invalid)
        }
    }

    @Test
    fun validatingAPatchWithAnInvalidOperationShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"op\": \"what\"}]")
            JsonPatch.validate(invalid)
        }
    }
}