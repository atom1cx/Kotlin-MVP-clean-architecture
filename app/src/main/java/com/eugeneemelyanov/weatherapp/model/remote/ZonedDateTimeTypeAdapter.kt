package com.eugeneemelyanov.weatherapp.model.remote

import com.google.gson.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import timber.log.Timber
import java.lang.reflect.Type
import java.text.ParseException

class ZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {

    companion object {
        private val TAG = ZonedDateTimeDeserializer::class.java.simpleName
    }

    @Synchronized
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): ZonedDateTime = try {
        val timestamp = jsonElement.asLong
        val instant = Instant.ofEpochSecond(timestamp)
        ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    } catch (e: ParseException) {
        Timber.tag(TAG).e(e)
        throw JsonSyntaxException(jsonElement.asString, e)
    }
}