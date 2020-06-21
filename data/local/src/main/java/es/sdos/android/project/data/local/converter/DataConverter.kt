package es.sdos.android.project.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.sdos.android.project.data.local.games.dbo.RoundDbo

class DataConverter {

    @TypeConverter
    fun fromCountryLangList(countryLang: List<RoundDbo>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<RoundDbo>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<RoundDbo>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<RoundDbo>?>() {}.type
        return gson.fromJson<List<RoundDbo>>(countryLangString, type)
    }

}