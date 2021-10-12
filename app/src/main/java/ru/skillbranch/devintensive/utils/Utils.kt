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
        lat = cyr!!.replace("а".toRegex(), "a")
        lat = lat.replace("б".toRegex(), "b")
        lat = lat.replace("в".toRegex(), "v")
        lat = lat.replace("г".toRegex(), "g")
        lat = lat.replace("д".toRegex(), "d")
        lat = lat.replace("е".toRegex(), "e")
        lat = lat.replace("ё".toRegex(), "e")
        lat = lat.replace("ж".toRegex(), "zh")
        lat = lat.replace("з".toRegex(), "z")
        lat = lat.replace("и".toRegex(), "i")
        lat = lat.replace("й".toRegex(), "i")
        lat = lat.replace("к".toRegex(), "k")
        lat = lat.replace("л".toRegex(), "l")
        lat = lat.replace("м".toRegex(), "m")
        lat = lat.replace("н".toRegex(), "n")
        lat = lat.replace("о".toRegex(), "o")
        lat = lat.replace("п".toRegex(), "p")
        lat = lat.replace("р".toRegex(), "r")
        lat = lat.replace("с".toRegex(), "s")
        lat = lat.replace("т".toRegex(), "t")
        lat = lat.replace("у".toRegex(), "u")
        lat = lat.replace("ф".toRegex(), "f")
        lat = lat.replace("х".toRegex(), "h")
        lat = lat.replace("ц".toRegex(), "c")
        lat = lat.replace("ч".toRegex(), "ch")
        lat = lat.replace("ш".toRegex(), "sh")
        lat = lat.replace("щ".toRegex(), "sh")
        lat = lat.replace("ъ".toRegex(), "")
        lat = lat.replace("ы".toRegex(), "i")
        lat = lat.replace("ь".toRegex(), "-")
        lat = lat.replace("э".toRegex(), "e")
        lat = lat.replace("ю".toRegex(), "yu")
        lat = lat.replace("я".toRegex(), "ya")

        //Capital letters
        lat = lat.replace("А".toRegex(), "A")
        lat = lat.replace("Б".toRegex(), "B")
        lat = lat.replace("В".toRegex(), "V")
        lat = lat.replace("Г".toRegex(), "G")
        lat = lat.replace("Д".toRegex(), "D")
        lat = lat.replace("Е".toRegex(), "E")
        lat = lat.replace("Ё".toRegex(), "E")
        lat = lat.replace("Ж".toRegex(), "ZH")
        lat = lat.replace("З".toRegex(), "Z")
        lat = lat.replace("И".toRegex(), "I")
        lat = lat.replace("Л".toRegex(), "I")
        lat = lat.replace("К".toRegex(), "K")
        lat = lat.replace("Л".toRegex(), "L")
        lat = lat.replace("М".toRegex(), "M")
        lat = lat.replace("Н".toRegex(), "N")
        lat = lat.replace("О".toRegex(), "O")
        lat = lat.replace("П".toRegex(), "P")
        lat = lat.replace("Р".toRegex(), "R")
        lat = lat.replace("С".toRegex(), "S")
        lat = lat.replace("Т".toRegex(), "T")
        lat = lat.replace("У".toRegex(), "U")
        lat = lat.replace("Ф".toRegex(), "F")
        lat = lat.replace("Х".toRegex(), "H")
        lat = lat.replace("Ц".toRegex(), "C")
        lat = lat.replace("Ч".toRegex(), "CH")
        lat = lat.replace("Ш".toRegex(), "SH")
        lat = lat.replace("Щ".toRegex(), "SH")
        lat = lat.replace("Ъ".toRegex(), "")
        lat = lat.replace("Ы".toRegex(), "I")
        lat = lat.replace("Ь".toRegex(), "")
        lat = lat.replace("Э".toRegex(), "E")
        lat = lat.replace("Ю".toRegex(), "YU")
        lat = lat.replace("Я".toRegex(), "YA")
        return lat
    }



}