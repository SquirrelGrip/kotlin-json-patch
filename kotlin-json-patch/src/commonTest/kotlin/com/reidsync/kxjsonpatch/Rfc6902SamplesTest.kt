package com.reidsync.kxjsonpatch

import resources.testdata.TestData_RFC6902_SAMPLES
import kotlin.test.Test

class Rfc6902SamplesTest : AbstractTest() {
    override fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_RFC6902_SAMPLES)
    }

    @Test
    fun childTest() {
        test()
    }
}