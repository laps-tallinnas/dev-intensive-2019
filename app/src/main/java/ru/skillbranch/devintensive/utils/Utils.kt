package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName (fullName:String?):Pair<String?, String?>{
            when (fullName){
                null -> return Pair (null, null)
                "" -> return Pair (null, null)
                " " -> return Pair (null, null)
                }
            val parts:List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            if (lastName == firstName) { return Pair (firstName, null) }
            else if (lastName =="") {return Pair (firstName, null)}
            else { return firstName to lastName }

    }

    fun toInitials (firstName:String?, lastName:String?):String {
        /*when(lastName) {
            null -> return "null"
        }
        val parts:List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        */
        if (firstName == null && lastName == null)
            return "null"

        if (firstName?.length!! > 1 && lastName == null) {
            return (firstName?.first().toString().toUpperCase())
        }
        if (firstName?.length!! > 1 && lastName?.length!!<1) {
            return firstName?.first().toString().toUpperCase()
        }
        if (firstName==null  && lastName?.length!!>1) {
            return lastName?.first().toString().toUpperCase()
        }

        if (firstName == null || lastName == null)
            return "null"

        if ((lastName == "" || lastName == " ") && firstName?.length!! > 1) {
            return firstName?.first().toString().toUpperCase()
        }

        if ((firstName == "" || firstName == " ") && lastName?.length!! > 1) {
            return lastName?.first().toString().toUpperCase()
        } else if (firstName?.length!! > 1 && lastName?.length!! > 1) {
            return firstName?.first().toString().toUpperCase() + lastName?.first().toString().toUpperCase()
        }
        else return "null"
    }

    fun transliteration (fullName:String?, delimiter:String = " "):String {
        when (fullName) {
          null -> return "null"
        }

        val parts:List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        print ("$firstName")
        print ("$lastName")
        val newName = convertRU(firstName)
        val newLastName = convertRU(lastName)
        return "$newName$delimiter$newLastName"
    }

    fun convertRU(cyr: String?): String {

        var lat: String

        //Lower case letters
        lat = cyr!!.replace("??".toRegex(), "a")
        lat = lat.replace("??".toRegex(), "b")
        lat = lat.replace("??".toRegex(), "v")
        lat = lat.replace("??".toRegex(), "g")
        lat = lat.replace("??".toRegex(), "d")
        lat = lat.replace("??".toRegex(), "e")
        lat = lat.replace("??".toRegex(), "e")
        lat = lat.replace("??".toRegex(), "zh")
        lat = lat.replace("??".toRegex(), "z")
        lat = lat.replace("??".toRegex(), "i")
        lat = lat.replace("??".toRegex(), "i")
        lat = lat.replace("??".toRegex(), "k")
        lat = lat.replace("??".toRegex(), "l")
        lat = lat.replace("??".toRegex(), "m")
        lat = lat.replace("??".toRegex(), "n")
        lat = lat.replace("??".toRegex(), "o")
        lat = lat.replace("??".toRegex(), "p")
        lat = lat.replace("??".toRegex(), "r")
        lat = lat.replace("??".toRegex(), "s")
        lat = lat.replace("??".toRegex(), "t")
        lat = lat.replace("??".toRegex(), "u")
        lat = lat.replace("??".toRegex(), "f")
        lat = lat.replace("??".toRegex(), "h")
        lat = lat.replace("??".toRegex(), "c")
        lat = lat.replace("??".toRegex(), "ch")
        lat = lat.replace("??".toRegex(), "sh")
        lat = lat.replace("??".toRegex(), "sh")
        lat = lat.replace("??".toRegex(), "")
        lat = lat.replace("??".toRegex(), "i")
        lat = lat.replace("??".toRegex(), "-")
        lat = lat.replace("??".toRegex(), "e")
        lat = lat.replace("??".toRegex(), "yu")
        lat = lat.replace("??".toRegex(), "ya")

        //Capital letters
        lat = lat.replace("??".toRegex(), "A")
        lat = lat.replace("??".toRegex(), "B")
        lat = lat.replace("??".toRegex(), "V")
        lat = lat.replace("??".toRegex(), "G")
        lat = lat.replace("??".toRegex(), "D")
        lat = lat.replace("??".toRegex(), "E")
        lat = lat.replace("??".toRegex(), "E")
        lat = lat.replace("??".toRegex(), "ZH")
        lat = lat.replace("??".toRegex(), "Z")
        lat = lat.replace("??".toRegex(), "I")
        lat = lat.replace("??".toRegex(), "I")
        lat = lat.replace("??".toRegex(), "K")
        lat = lat.replace("??".toRegex(), "L")
        lat = lat.replace("??".toRegex(), "M")
        lat = lat.replace("??".toRegex(), "N")
        lat = lat.replace("??".toRegex(), "O")
        lat = lat.replace("??".toRegex(), "P")
        lat = lat.replace("??".toRegex(), "R")
        lat = lat.replace("??".toRegex(), "S")
        lat = lat.replace("??".toRegex(), "T")
        lat = lat.replace("??".toRegex(), "U")
        lat = lat.replace("??".toRegex(), "F")
        lat = lat.replace("??".toRegex(), "H")
        lat = lat.replace("??".toRegex(), "C")
        lat = lat.replace("??".toRegex(), "CH")
        lat = lat.replace("??".toRegex(), "SH")
        lat = lat.replace("??".toRegex(), "SH")
        lat = lat.replace("??".toRegex(), "")
        lat = lat.replace("??".toRegex(), "I")
        lat = lat.replace("??".toRegex(), "")
        lat = lat.replace("??".toRegex(), "E")
        lat = lat.replace("??".toRegex(), "YU")
        lat = lat.replace("??".toRegex(), "YA")
        return lat
    }



}