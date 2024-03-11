package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import resources.testdata.TestData_DIFF
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonDiffTest2 {
    var objectMapper = GsonObjectMapper()
    lateinit var jsonNode: JsonArray
    @BeforeTest
    fun setUp() {
        jsonNode = objectMapper.readTree(TestData_DIFF).jsonArray
    }

    @Test
    fun testPatchAppliedCleanly() {
        for (i in 0 until jsonNode.size) {
            val first: JsonElement = jsonNode.get(i).jsonObject.get("first")!!
            val second: JsonElement = jsonNode.get(i).jsonObject.get("second")!!
            val patch: JsonArray = jsonNode.get(i).jsonObject.get("patch")!!.jsonArray
            val message: String = jsonNode.get(i).jsonObject.get("message").toString()
            println("Test # $i")
            println(first)
            println(second)
            println(patch)
            val secondPrime: JsonElement = JsonPatch.apply(patch, first)
            println(secondPrime)
            assertEquals(secondPrime, second, message)
        }
    }
}