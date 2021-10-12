package ru.skillbranch.devintensive

import android.text.Html
import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.*
import java.util.*
import kotlin.time.TestClock

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
    fun test_initials() {
        val translated:String = Utils.toInitials(null)
        println ("$translated")
    }

    @Test
    fun test_transliteration() {
        val translated:String = Utils.transliteration("Mihail Svetlov")
        println ("$translated")
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
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),payload ="any text message", type ="text", isIncoming = true)
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload ="any image url", type ="image", isIncoming = false)
        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
        }

    @Test
    fun test_date (){
        val date: Date = Date()
        println (date.format("hh 'o''clock' a, zzzz"))
    }

    @Test
    fun check_date_add() {
        val date: Date = Date().add(2, TimeUnits.DAY)
        print (date.format())
    }

    @Test
    fun check_humanize_diff (date:Date): Unit {
        val currentDate = Date()

    }
    }

