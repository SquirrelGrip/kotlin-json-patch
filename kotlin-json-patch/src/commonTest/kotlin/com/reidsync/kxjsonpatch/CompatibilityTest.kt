package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CompatibilityTest {
    var mapper: GsonObjectMapper = GsonObjectMapper()
    var addNodeWithMissingValue: JsonElement = mapper.readTree("[{\"op\":\"add\",\"path\":\"a\"}]")
    var replaceNodeWithMissingValue: JsonElement = mapper.readTree("[{\"op\":\"replace\",\"path\":\"a\"}]")

    @BeforeTest
    fun setUp() {
        mapper = GsonObjectMapper()
        addNodeWithMissingValue = mapper.readTree("[{\"op\":\"add\",\"path\":\"a\"}]")
        replaceNodeWithMissingValue = mapper.readTree("[{\"op\":\"replace\",\"path\":\"a\"}]")
    }

    @Test
    fun withFlagAddShouldTreatMissingValuesAsNulls() {
        val expected: JsonElement = mapper.readTree("{\"a\":null}")
        val result: JsonElement = JsonPatch.apply(
            addNodeWithMissingValue,
            JsonObject(emptyMap()),
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
        assertEquals(result, expected)
    }

    @Test
    fun withFlagAddNodeWithMissingValueShouldValidateCorrectly() {
        JsonPatch.validate(
            addNodeWithMissingValue,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
    }

    @Test
    fun withFlagReplaceShouldTreatMissingValuesAsNull() {
        val source: JsonElement = mapper.readTree("{\"a\":\"test\"}")
        val expected: JsonElement = mapper.readTree("{\"a\":null}")
        val result: JsonElement = JsonPatch.apply(
            replaceNodeWithMissingValue,
            source,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
        assertEquals(
            result,
            expected
        )
    }

    @Test
    fun withFlagReplaceNodeWithMissingValueShouldValidateCorrectly() {
        JsonPatch.validate(
            addNodeWithMissingValue,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
    }
}