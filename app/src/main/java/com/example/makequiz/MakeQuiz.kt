package com.example.makequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColorInt
import java.util.ArrayList

class MakeQuiz : AppCompatActivity()
{
    private var questionText: EditText? = null
    private var answerText: EditText? = null
    private var questionAnswer: TextView? = null
    private var listQuestionAnswer: String? = null
    private var arrayQuestion: ArrayList<String>? = null
    private var arrayAnswer: ArrayList<String>? = null


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_quiz)
        questionText = findViewById(R.id.question)
        answerText = findViewById(R.id.answer)
        questionAnswer = findViewById(R.id.questionAnswer)
        questionText?.requestFocus()
        listQuestionAnswer = ""
        arrayAnswer = ArrayList()
        arrayQuestion = ArrayList()
    }




    fun addQuestionAnswer(view : View)
    {
        if(questionText?.text.toString() == "" || answerText?.text.toString() == "")
        {
            Toast.makeText(this,"La pregunta-respuesta no puede ser agregada ya que están vacios los campos", Toast.LENGTH_LONG).show()
        }
        else
        {
            questionAnswerAccept()
        }
    }

    private fun questionAnswerAccept()
    {
        answerText?.clearFocus()
        for (question in arrayQuestion!!)
        {
            if (question == questionText?.text.toString().removeSuffix("?").removeSuffix("¿"))
            {
                Toast.makeText(this, "Esta pregunta ya se encuentra en la lista de preguntas y " +
                        "respuestas, favor de ingresar otra", Toast.LENGTH_LONG).show()
                return
            }
        }
        arrayAnswer?.add(answerText?.text.toString())
        arrayQuestion?.add(questionText?.text.toString().removeSuffix("?").removeSuffix("¿"))
        listQuestionAnswer = listQuestionAnswer + arrayQuestion?.size +"- ¿<font color=#DD3220>" +
        questionText?.text+
                "?: </font><font color=#36B536>" + answerText?.text + "</font><br>"
        questionAnswer?.text = Html.fromHtml(listQuestionAnswer)
        questionText?.setText("")
        answerText?.setText("")
    }

    private fun saveDataToServer()
    {
        
    }
}