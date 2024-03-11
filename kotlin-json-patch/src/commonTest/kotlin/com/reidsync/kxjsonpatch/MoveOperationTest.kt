package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonElement
import resources.testdata.TestData_MOVE
import kotlin.test.Test
import kotlin.test.assertEquals

class MoveOperationTest : AbstractTest() {
    private val MAPPER = GsonObjectMapper()
    //@org.junit.runners.Parameterized.Parameters
    override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_MOVE)
    }

    @Test
    fun testMoveValueGeneratedHasNoValue() {
        val jsonNode1: JsonElement =
            MAPPER.readTree("{ \"foo\": { \"bar\": \"baz\", \"waldo\": \"fred\" }, \"qux\": { \"corge\": \"grault\" } }")
        val jsonNode2: JsonElement =
            MAPPER.readTree("{ \"foo\": { \"bar\": \"baz\" }, \"qux\": { \"corge\": \"grault\", \"thud\": \"fred\" } }")
        val patch: JsonElement =
            MAPPER.readTree("[{\"op\":\"move\",\"from\":\"/foo/waldo\",\"path\":\"/qux/thud\"}]")
        val diff: JsonElement = JsonDiff.asJson(jsonNode1, jsonNode2)
        assertEquals(diff, patch)
    }


    @Test
    fun testMoveArrayGeneratedHasNoValue() {
        val jsonNode1: JsonElement =
            MAPPER.readTree("{ \"foo\": [ \"all\", \"grass\", \"cows\", \"eat\" ] }")
        val jsonNode2: JsonElement =
            MAPPER.readTree("{ \"foo\": [ \"all\", \"cows\", \"eat\", \"grass\" ] }")
        val patch: JsonElement =
            MAPPER.readTree("[{\"op\":\"move\",\"from\":\"/foo/1\",\"path\":\"/foo/3\"}]")
        val diff: JsonElement = JsonDiff.asJson(jsonNode1, jsonNode2)
        assertEquals(diff, patch)
    }

    @Test
    fun childTest() {
        test()
    }
}