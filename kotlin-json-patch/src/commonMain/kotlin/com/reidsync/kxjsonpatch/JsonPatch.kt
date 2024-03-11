package com.reidsync.kxjsonpatch

import com.reidsync.kxjsonpatch.Operation.*
import kotlinx.serialization.json.*
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

object JsonPatch {
    private fun getPatchAttr(jsonNode: JsonObject, attr: String): JsonElement {
        val child = jsonNode.get(attr) ?: throw InvalidJsonPatchException("Invalid JSON Patch payload (missing '$attr' field)")
        return child
    }

    private fun getPatchAttrWithDefault(jsonNode: JsonObject, attr: String, defaultValue: JsonElement): JsonElement {
        val child = jsonNode.get(attr)
        if (child == null)
            return defaultValue
        else
            return child
    }

    @Throws(InvalidJsonPatchException::class)
    private fun process(patch: JsonElement, processor: JsonPatchApplyProcessor, flags: Set<CompatibilityFlags>) {

        if (patch !is JsonArray)
            throw InvalidJsonPatchException("Invalid JSON Patch payload (not an array)")
        val operations = patch.jsonArray.iterator()
        while (operations.hasNext()) {
            val jsonNode_ = operations.next()
            if (jsonNode_ !is JsonObject) throw InvalidJsonPatchException("Invalid JSON Patch payload (not an object)")
            val jsonNode = jsonNode_.jsonObject
            val operation = Operations.opFromName(getPatchAttr(jsonNode.jsonObject, Constants.OP).toString().replace("\"".toRegex(), ""))
            val path = getPath(getPatchAttr(jsonNode, Constants.PATH))

            when (operation) {
                REMOVE -> {
                    processor.edit { remove(path) }
                }

                ADD -> {
                    val value: JsonElement
                    if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS))
                        value = getPatchAttr(jsonNode, Constants.VALUE)
                    else
                        value = getPatchAttrWithDefault(jsonNode, Constants.VALUE, JsonNull)
                    processor.edit { add(path, value) }
                }

                REPLACE -> {
                    val value: JsonElement
                    if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS))
                        value = getPatchAttr(jsonNode, Constants.VALUE)
                    else
                        value = getPatchAttrWithDefault(jsonNode, Constants.VALUE, JsonNull)
                    processor.edit { replace(path, value) }
                }

                MOVE -> {
                    val fromPath = getPath(getPatchAttr(jsonNode, Constants.FROM))
                    processor.edit { move(fromPath, path) }
                }

                COPY -> {
                    val fromPath = getPath(getPatchAttr(jsonNode, Constants.FROM))
                    processor.edit { copy(fromPath, path) }
                }

                TEST -> {
                    val value = if (!flags.contains(CompatibilityFlags.MISSING_VALUES_AS_NULLS))
                        getPatchAttr(jsonNode, Constants.VALUE)
                    else
                        getPatchAttrWithDefault(jsonNode, Constants.VALUE, JsonNull)
                    processor.edit { test(path, value) }
                }
            }
        }
    }

    @Throws(InvalidJsonPatchException::class)
    @JvmStatic
    @JvmOverloads
    fun validate(patch: JsonElement, flags: Set<CompatibilityFlags> = CompatibilityFlags.defaults()) {
        process(patch, NoopProcessor.INSTANCE, flags)
    }

    @Throws(JsonPatchApplicationException::class)
    @JvmStatic
    @JvmOverloads
    fun apply(patch: JsonElement, source: JsonElement, flags: Set<CompatibilityFlags> = CompatibilityFlags.defaults()): JsonElement {
        val processor = ApplyProcessor(source)
        process(patch, processor, flags)
        return processor.result()
    }


    private fun decodePath(path: String): String {
        return path.replace("~1".toRegex(), "/").replace("~0".toRegex(), "~") // see http://tools.ietf.org/html/rfc6901#section-4
    }

    private fun getPath(path: JsonElement): List<String> {
        //        List<String> paths = Splitter.on('/').splitToList(path.toString().replaceAll("\"", ""));
        //        return Lists.newArrayList(Iterables.transform(paths, DECODE_PATH_FUNCTION));
        val pathstr = path.toString().replace("\"", "")
        val paths = pathstr.split("/")
        return paths.map { decodePath(it) }
    }
}
