package ru.skillbranch.devintensive.repository

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {
    private const val NAME = "NAME"
    private const val ADDRESS = "ADDRESS"
    private val prefs: SharedPreferences by lazy{
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun saveProfile(profile: Profile) {
        with (profile){
            putValue(NAME to name) // key is local, value is from repository data class
            putValue(ADDRESS to address)
        }
    }

    fun getProfile(): Profile = Profile (
        prefs.getString(NAME, "")!!, 
        prefs.getString(ADDRESS,"")!!
            )

    private fun putValue (pair: Pair<String, Any>) = with(prefs.edit()){
        val key:String = pair.first
        val value = pair.second
        when(value){
            is String -> putString(key, value)
            is Int -> putInt (key, value)
            is Boolean  -> putBoolean(key, value)
            is Float -> putFloat (key, value)
            else -> error("value error, only primitive types are supported")
        }
        apply()
}
}