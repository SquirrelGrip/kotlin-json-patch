package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import resources.testdata.TestData_SAMPLE
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit test
 */
class JsonDiffTest {
    var objectMapper = GsonObjectMapper()
    lateinit var jsonNode: JsonArray
    @BeforeTest
    fun setUp() {
        jsonNode = objectMapper.readTree(TestData_SAMPLE).jsonArray
    }

    @Test
    fun testSampleJsonDiff() {
        for (i in 0 until jsonNode.size) {
            val first: JsonElement = jsonNode.get(i).jsonObject.get("first")!!
            val second: JsonElement = jsonNode.get(i).jsonObject.get("second")!!
            println("Test # $i")
            println(first)
            println(second)
            val actualPatch: JsonElement = JsonDiff.asJson(first, second)
            println(actualPatch)
            val secondPrime: JsonElement = JsonPatch.apply(actualPatch, first)
            println(secondPrime)
            assertEquals(second, secondPrime)
        }
    }

    @Test
    fun testGeneratedJsonDiff() {
        for (i in 0..999) {
            val first: JsonElement = TestDataGenerator.generate((0..10).random())
            val second: JsonElement = TestDataGenerator.generate((0..10).random())
            val actualPatch: JsonElement = JsonDiff.asJson(first, second)
            println("Test # $i")
            println(first)
            println(second)
            println(actualPatch)
            val secondPrime: JsonElement = JsonPatch.apply(actualPatch, first)
            println(secondPrime)
            assertEquals(second, secondPrime)
        }
    }
}