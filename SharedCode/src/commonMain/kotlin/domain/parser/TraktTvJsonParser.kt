package org.kotlin.mpp.mobile.domain.parser

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonTreeParser
import kotlinx.serialization.json.content

object TraktTvJsonParser {

    fun parse(jsonString: String):List<String>{
        val response: JsonArray = JsonTreeParser(jsonString).readFully().jsonArray
        return response
            .asSequence()
            .map {it.jsonObject}
            .map { it.getObject("ids") }
            .map { it.get("imdb") }
            .map { it.content }
            .toList()
    }
}