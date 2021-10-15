package ru.skillbranch.devintensive.models
import android.util.Log
class Bender (var status:Status=Status.NORMAL, var question: Question = Question.NAME){
    private val TAG:String  = "Bender"
    var retry : Int = 0
    lateinit var total : Pair <String?, Boolean>

    fun askQuestion(): String {
        when (question){
            Question.NAME -> return Question.NAME.question
            Question.PROFESSION -> return Question.PROFESSION.question
            Question.BDAY -> return Question.BDAY.question
            Question.SERIAL->return Question.SERIAL.question
            Question.IDLE-> return Question.IDLE.question
            else -> return question.question
        }
    }



    fun listenAnswer(answer:String):Pair <String, Triple<Int, Int, Int>> {
        Log.d(TAG, "answer: {$answer}")
        if (question?.nextQuestion() == Question.NAME) {
            return Pair("Отлично - ты справился\nНа этом все, вопросов больше нет", status.color)
        } else {
            var total  = validateAnswer(answer);
            if (total.second == true)
                {
                    retry++
                    when (retry) {
                        3 -> {
                                resetBender()
                                return Pair("Это неправильный ответ. Давай все по новой\n${question.question}", status.color)
                        }
                        else ->  {
                            status = status.nextStatus()
                            return Pair("${total.first} ${question.question}", status.color)
                        }
                    }

                }
                else {
                    question= question.nextQuestion()
                    return Pair("Отлично - ты справился. ${question.question}", status.color)
                }
            }
        }

    fun validateAnswer(answer:String): Pair<String?, Boolean> {
        when (question) {
            Question.NAME -> return validateName(answer)
            Question.PROFESSION -> return  validateProfession(answer)
            Question.MATERIAL -> return validateMaterial(answer)
            Question.BDAY->return validateBday(answer)
            Question.SERIAL->return validateSerial(answer)
            Question.IDLE->return Pair (answer, false)
            else -> return Pair ("Error",true)
        }

    }

    fun validateName(answer:String):Pair<String, Boolean> {
        val first = answer[0]
        if(first.isUpperCase() && (answer.toLowerCase() in Question.NAME.answers)){
            return Pair (answer, false)
        }
        else
            return Pair("Имя должно начинаться с заглавной буквы", true)
    }

    fun validateProfession(answer:String):Pair<String, Boolean> {
        val first = answer[0]
        if(answer in Question.PROFESSION.answers && first.isLowerCase()){
            return Pair (answer, false)
        }
        else
            return Pair("Профессия должна начинаться со строчной буквы", true)
    }


    fun validateMaterial(answer:String):Pair<String, Boolean>  {
        if(answer.contains('1') || answer.contains('2') || answer.contains('3') || answer.contains('4') || answer.contains('5')|| answer.contains('6') || answer.contains('7')){
                return Pair ("Материал не должен содержать цифр", true)
            }
        else
        {return Pair (answer, false)}
    }

    fun validateBday(answer:String):Pair<String, Boolean> {
        if(answer.contains('a') || answer.contains('b') || answer.contains('c') || answer.contains('d') || answer.contains('e')|| answer.contains('f') || answer.contains('g')){
                return Pair ("Год моего рождения должен содержать только цифры", true)
            }
        else
            return Pair (answer, false)
    }

    fun validateSerial(answer:String):Pair<String, Boolean> {
        if(answer.length==7 && (answer.contains('1') || answer.contains('2') || answer.contains('3') || answer.contains('4') || answer.contains('5')|| answer.contains('6') || answer.contains('7')) && (answer in Question.SERIAL.answers)){
            return Pair (answer, false)
            }
        else
            return Pair ("Серийный номер содержит только цифры, и их 7", true)
    }

    fun resetBender():Unit
    {
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
        NAME(question = "Как меня зовут?",listOf("бендер","bender")) {
            override fun nextQuestion(): Question =PROFESSION
                                                                   },
        PROFESSION(question = "Назови свою профессию?",listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question =MATERIAL
        },
        MATERIAL(question = "Из чего я сделан?",listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question =BDAY
        },
        BDAY(question = "Когда меня создали ?",listOf("2993")) {
            override fun nextQuestion(): Question =SERIAL
        },
        SERIAL(question = "Какой мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question =IDLE
        },
        IDLE(question = "На этом все, вопросов больше нет",listOf()) {
            override fun nextQuestion(): Question = PROFESSION
            };


        abstract fun nextQuestion (): Question
        }

    }


