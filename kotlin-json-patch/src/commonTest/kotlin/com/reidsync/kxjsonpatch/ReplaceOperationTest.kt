package com.reidsync.kxjsonpatch

import resources.testdata.TestData_REPLACE
import kotlin.test.Test

class ReplaceOperationTest : AbstractTest() {
//    @org.junit.runners.Parameterized.Parameters
    override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_REPLACE)
    }

    @Test
    fun childTest() {
        test()
    }
}