package ru.skillbranch.devintensive

import android.text.Html
import org.junit.Test
import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun simpleHello (){
        System.out.print ("Hello")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun test_instance() {
        val user = User("0", "John", "Dow")
        user.printMe()
    }

    @Test
    fun test_factory() {
        var user = User.makeUser("John Dow")
        var user2 = User.makeUser("John Cena")
        var user3 = User.makeUser("Joe Doe")
        user.printMe()
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
    }

    @Test
    fun test_full_parse() {
        val parsed:Pair<String?, String?> = Utils.parseFullName("Andrey ")
        System.out.println("${parsed.first} ${parsed.second}")
    }

    @Test
    fun test_initials() {
        val translated:String = Utils.toInitials(" ", "")
        System.out.println ("$translated")
    }

    @Test
    fun test_transliteration() {
        val translated:String = Utils.transliteration("Михаил Светлов")
        System.out.println  ("$translated")
    }

    @Test
    fun test_dataq_mapping (){
        val user = User.makeUser("Макеев Михаил")
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory (){
        val user = User.makeUser("Макеев Михаил")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),Date(), "text","any text message",  isIncoming = true)
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),Date(),"image", "any image url",  isIncoming = false)
        System.out.println (txtMessage.formatMessage())
        System.out.println (imgMessage.formatMessage())
        }

    @Test
    fun test_date (){
        val date: Date = Date()
        System.out.println  (date.format("hh 'o''clock' a, zzzz"))
    }

    @Test
    fun check_date_add() {
        val date: Date = Date().add(2, TimeUnits.DAY)
        System.out.println  (date.format())
    }

    @Test
    fun check_humanize_diff () {
        val currentDate = Date().add(362, TimeUnits.DAY)

        System.out.println(currentDate.humanizeDiff())

    }

    @Test
    fun check_birthday () {
        val BenderObj = Bender()
        val result:Pair <String?, Boolean> = BenderObj.validateBday("2465")
        System.out.print(result)

    }

    @Test
    fun check_serial () {
        val BenderObj = Bender()
        val result:Pair <String?, Boolean> = BenderObj.validateSerial("2716058")
        System.out.print(result)

    }

}

