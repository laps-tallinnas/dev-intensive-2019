package ru.skillbranch.devintensive.models

class Bender (var status:Status=Status.NORMAL, var question: Question = Question.NAME){
    var retry : Int = 0


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
        if (question?.nextQuestion() == Question.PROFESSION) {
            return Pair("Отлично - ты справился\nНа этом все, вопросов больше нет", status.color)
        } else {
            retry++
            when (retry) {
                3 -> {
                    resetBender()
                    return Pair("Это неправильный ответ. Давай все по новой\n${question.question}", status.color)
                }
                else -> {
                    validateAnswer(answer);
                    status = status.nextStatus()
                    return Pair("Это неправильный ответ. ${question.question}", status.color)
                }
            }
        }
    }

    fun validateAnswer(): String {
        when (question) {
            Question.NAME -> return validateName(question.question)
            Question.PROFESSION -> return  validateProfession(question.question)
        }

    }

    fun validateName(answer:String):String {
        if(answer.first().isUpperCase()){
            return answer
        }
        else
            return "Имя должно начинаться с заглавной буквы"
    }

    fun validateProfession(answer:String):String {
        if(answer.first().isUpperCase()){
            return "Профессия должна начинаться со строчной буквы"
        }
        else
            return answer
    }

    fun validateBirthday(answer:String):String {
        val symbols = "0123456789"
        if(answer.contains('1','2','3'){
            return "Профессия должна начинаться со строчной буквы"
        }
        else
            return answer
    }

    fun resetBender():Unit
    {
        status=Status.NORMAL
        question = Question.NAME
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


