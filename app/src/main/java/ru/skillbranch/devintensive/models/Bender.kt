package ru.skillbranch.devintensive.models
import android.util.Log
class Bender (var status:Status=Status.NORMAL, var question: Question = Question.NAME){
    private val TAG:String  = "Bender"
    private var retry : Int = 0
    private val success :String = "Отлично - ты справился"
    private val end:String = "На этом все, вопросов больше нет"
    private val wrong_answer:String = "Это неправильный ответ"
    private val resetString:String = "Это неправильный ответ. Давай все по новой"

    fun askQuestion(): String {
        return when (question){
            Question.NAME -> Question.NAME.question
            Question.PROFESSION -> Question.PROFESSION.question
            Question.MATERIAL -> Question.MATERIAL.question
            Question.BDAY -> Question.BDAY.question
            Question.SERIAL-> Question.SERIAL.question
            Question.IDLE-> Question.IDLE.question
            else -> question.question
        }
    }



    fun listenAnswer(answer:String):Pair <String, Triple<Int, Int, Int>> {
//        Log.d(TAG, "answer: {$answer}")
        if (question == Question.IDLE) {
            return Pair("${success}\n${end}", status.color)
        } else {
            val total  = validateAnswer(answer)
            if (total.second)
                {
                    retry++
                    return when (retry) {
                        3 -> {
                            resetBender()
                            Pair("${resetString}\n${question.question}", status.color)
                        }
                        else ->  {
                            status = status.nextStatus()
                            //TODO total.first
                            Pair("${total.first}\n${question.question}", status.color)
                        }
                    }

                }
                else {
                    question= question.nextQuestion()
                    return Pair("${success}\n${question.question}", status.color)
                }
            }
        }

    private fun validateAnswer(answer:String): Pair<String?, Boolean> {
        return when (question) {
            Question.NAME -> validateName(answer)
            Question.PROFESSION -> validateProfession(answer)
            Question.MATERIAL -> validateMaterial(answer)
            Question.BDAY-> validateBday(answer)
            Question.SERIAL-> validateSerial(answer)
            Question.IDLE-> Pair (answer, false)
        }

    }

    fun validateName(answer:String):Pair<String, Boolean> {
        val first = answer[0]
        return if(first.isUpperCase()){
            if(answer in Question.NAME.answers)
            {
                Pair (answer, false)
            }
            else Pair (wrong_answer, true)
        } else
            Pair("Имя должно начинаться с заглавной буквы", true)
    }

    fun validateProfession(answer:String):Pair<String, Boolean> {
        val first = answer[0]
        return if(first.isLowerCase()){
            if (answer in Question.PROFESSION.answers)
                 Pair (answer, false)
            else Pair (wrong_answer, true)
        } else {
            Pair("Профессия должна начинаться со строчной буквы", true)
        }
    }


    private fun validateMaterial(answer:String):Pair<String, Boolean>  {
        if (answer.matches("^[a-zA-Z]*$".toRegex())) {
            if (answer in Question.MATERIAL.answers) {
                return Pair(answer, false)
            }
            else { return Pair(wrong_answer, true)
            }
        }
        else
            return Pair ("Материал не должен содержать цифр", true)
    }

    fun validateBday(answer:String):Pair<String, Boolean> {
        if (answer.matches("^[0-9]*$".toRegex())) {
            if (answer in Question.BDAY.answers) {
                return Pair(answer, false)
            }
         else { return Pair(wrong_answer, true)
                }
        }
        else
            return Pair ("Год моего рождения должен содержать только цифры", true)
    }

    fun validateSerial(answer:String):Pair<String, Boolean> {
        if(answer.length==7 && answer.matches("^[0-9]*$".toRegex())){
            if(answer in Question.SERIAL.answers)
            {
                return Pair (answer, false)
            }
            return Pair ("Это неправильный ответ", true)
        }
        else
            return Pair ("Серийный номер содержит только цифры, и их 7", true)
    }

    private fun resetBender() {
        status=Status.NORMAL
        question = Question.NAME
        retry = 0

    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal+1]
            }
            else {
                values()[0]
            }
        }
    }

    enum class Question(val question:String, val answers:List<String>){
        NAME(question = "Как меня зовут?",listOf("Бендер","Bender")) {
            override fun nextQuestion(): Question =PROFESSION
                                                                   },
        PROFESSION(question = "Назови мою профессию?",listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question =MATERIAL
        },
        MATERIAL(question = "Из чего я сделан?",listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question =BDAY
        },
        BDAY(question = "Когда меня создали?",listOf("2993")) {
            override fun nextQuestion(): Question =SERIAL
        },
        SERIAL(question = "Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question =IDLE
        },
        IDLE(question = "На этом все, вопросов больше нет",listOf()) {
            override fun nextQuestion(): Question = IDLE
            };


        abstract fun nextQuestion (): Question
        }

    }


