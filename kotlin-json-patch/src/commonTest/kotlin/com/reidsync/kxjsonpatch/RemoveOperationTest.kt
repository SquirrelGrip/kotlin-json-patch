package com.reidsync.kxjsonpatch

import resources.testdata.TestData_REMOVE
import kotlin.test.Test

class RemoveOperationTest : AbstractTest() {
    override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_REMOVE)
    }

    @Test
    fun childTest() {
        test()
    }
}