package com.cai.campus.common.network.gson.adapter

import com.google.gson.*
import java.lang.reflect.Type

/**
 * File descripition:   对返回值为空处理
 *
 * @author Administrator
 * @date 2018/5/21
 */
class DoubleDefaultAdapter : JsonSerializer<Double?>,
    JsonDeserializer<Double> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): Double {
        try {
            if (json.asString == "" || json.asString == "null") {
                return 0.00
            }
        } catch (ignore: Exception) {
        }
        return try {
            json.asDouble
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Double?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}