package com.reidsync.kxjsonpatch

enum class CompatibilityFlags {
    MISSING_VALUES_AS_NULLS;


    companion object {
        fun defaults(): Set<CompatibilityFlags> {
            return setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        }
    }
}
