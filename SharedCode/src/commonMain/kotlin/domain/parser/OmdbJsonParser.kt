package org.kotlin.mpp.mobile.domain.parser

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeParser
import kotlinx.serialization.json.content
import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse

object OmdbJsonParser {

    fun parse(jsonString: String):OmdbResponse{
        val response: JsonElement = JsonTreeParser(jsonString).read()
        val jsonObject: JsonObject = response.jsonObject
        return with(jsonObject) {
            OmdbResponse(
                getKey("Title"),
                getKey("Year"),
                getKey("Released"),
                getKey("Rated"),
                getKey("Runtime"),
                getKey("Genre"),
                getKey("Director"),
                getKey("Writer"),
                getKey("Actors"),
                getKey("Plot"),
                getKey("Language"),
                getKey("Country"),
                getKey("Awards"),
                getKey("Poster")
            )
        }
    }

    private fun JsonObject.getKey(key:String):String{
        return get(key).content
    }
}