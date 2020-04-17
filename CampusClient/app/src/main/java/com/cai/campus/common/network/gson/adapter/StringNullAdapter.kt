package com.cai.campus.common.network.gson.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class StringNullAdapter : TypeAdapter<String?>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): String? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        val jsonStr = reader.nextString()
        return if (jsonStr == "null") {
            ""
        } else {
            jsonStr
        }
    }

    @Throws(IOException::class)
    override fun write(
        writer: JsonWriter,
        value: String?
    ) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}