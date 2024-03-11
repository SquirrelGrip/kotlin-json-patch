package com.reidsync.kxjsonpatch

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.random.Random

object TestDataGenerator {
    private val random = Random(Int.MAX_VALUE)
    private val name: List<String> = arrayListOf<String>("summers", "winters", "autumn", "spring", "rainy")
    private val age: List<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val gender: List<String> = arrayListOf<String>("male", "female")
    private val country: List<String> = arrayListOf<String>(
        "india",
        "aus",
        "nz",
        "sl",
        "rsa",
        "wi",
        "eng",
        "bang",
        "pak"
    )
    private val friends: List<String> = arrayListOf<String>(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    )

    fun generate(count: Int): JsonElement {
        var jsonNode: JsonArray = JsonArray(emptyList())
        for (i in 0 until count) {
            var objectNode: JsonObject = JsonObject(emptyMap())
            objectNode.addProperty(
                "name",
                name[random.nextInt(name.size)]
            )
            objectNode.addProperty(
                "age",
                age[random.nextInt(age.size)]
            )
            objectNode.addProperty(
                "gender",
                gender[random.nextInt(gender.size)]
            )
            val countryNode: JsonArray = getArrayNode(
                country.subList(
                    random.nextInt(
                        country.size / 2
                    ), country.size / 2 + random.nextInt(country.size / 2)
                )
            )
            objectNode = objectNode.add("country", countryNode)
            val friendNode: JsonArray = getArrayNode(
                friends.subList(
                    random.nextInt(
                        friends.size / 2
                    ), friends.size / 2 + random.nextInt(friends.size / 2)
                )
            )
            objectNode = objectNode.add("friends", friendNode)
            jsonNode = jsonNode.add(objectNode)
        }
        return jsonNode
    }


    private fun getArrayNode(args: List<String>): JsonArray {
        val countryNode: JsonArray = JsonArray(emptyList())
        for (arg in args) {
            countryNode.add(JsonPrimitive(arg))
        }
        return countryNode
    }
}