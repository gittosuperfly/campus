package com.cai.campus.common.network.gson.adapter

import com.google.gson.*
import java.lang.reflect.Type

class IntegerDefaultAdapter : JsonSerializer<Int?>, JsonDeserializer<Int> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Int {
        try {
            if (json.asString == "" || json.asString == "null") {
                return 0
            }
        } catch (ignore: Exception) {
        }
        return try {
            json.asInt
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Int?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}