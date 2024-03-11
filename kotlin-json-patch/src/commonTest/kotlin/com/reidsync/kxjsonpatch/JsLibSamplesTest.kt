package com.reidsync.kxjsonpatch

import resources.testdata.TestData_JS_LIB_SAMPLES
import kotlin.test.Test

/**
 * @author ctranxuan (streamdata.io).
 *
 * These tests comes from JS JSON-Patch libraries (
 * https://github.com/Starcounter-Jack/JSON-Patch/blob/master/test/spec/json-patch-tests/tests.json
 * https://github.com/cujojs/jiff/blob/master/test/json-patch-tests/tests.json)
 */
class JsLibSamplesTest : AbstractTest() {
    //@org.junit.runners.Parameterized.Parameters
    override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_JS_LIB_SAMPLES)
    }

    @Test
    fun childTest() {
        test()
    }
}