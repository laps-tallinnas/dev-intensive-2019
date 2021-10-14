package ru.skillbranch.devintensive.models

class Bender (var status:Status=Status.NORMAL, var question: Question = Question.NAME){

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

        return if (question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично, это правильный ответ!.${question.question}" to status.color
            //TODO change status
        }
        else {
            status = status.nextStatus()
            "Это неправильный ответ. ${question.question}" to status.color
            // TODO change status
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,255,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

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
        PROFESSION(question = "Назови свою профессию?",listOf("бендер","bender")) {
            override fun nextQuestion(): Question =MATERIAL
        },
        MATERIAL(question = "Из чего я сделан?",listOf("metal", "металл", "iron", "wood","дерево")) {
            override fun nextQuestion(): Question =BDAY
        },
        BDAY(question = "Когда меня создали ?",listOf("2993")) {
            override fun nextQuestion(): Question =SERIAL
        },
        SERIAL(question = "Какой мой серийный номер?",listOf("2716057")) {
            override fun nextQuestion(): Question =IDLE
        },
        IDLE(question = "Нет больше вопросов",listOf()) {
            override fun nextQuestion(): Question = PROFESSION
            };


        abstract fun nextQuestion (): Question
        }

    }


