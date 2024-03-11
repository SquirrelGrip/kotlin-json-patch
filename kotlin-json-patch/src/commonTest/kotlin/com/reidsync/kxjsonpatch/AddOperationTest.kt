package com.reidsync.kxjsonpatch

import resources.testdata.TestData_ADD
import kotlin.test.Test

class AddOperationTest : AbstractTest() {
     override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_ADD)
    }

    @Test
    fun childTest() {
        test()
    }
}