package ru.skillbranch.devintensive.models

data class Profile(val name:String,
                   val address:String) {
    val nickname:String = name+"_"
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name,
        "address" to address)
    }

