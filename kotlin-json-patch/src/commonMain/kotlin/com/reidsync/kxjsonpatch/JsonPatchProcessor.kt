package com.reidsync.kxjsonpatch;

import kotlinx.serialization.json.*

interface JsonPatchProcessor {
    fun remove(path: List<String>)
    fun replace(path: List<String>, value: JsonElement)
    fun add(path: List<String>, value: JsonElement)
    fun move(fromPath: List<String>, toPath: List<String>)
    fun copy(fromPath: List<String>, toPath: List<String>)
    fun test(path: List<String>, value: JsonElement)
}