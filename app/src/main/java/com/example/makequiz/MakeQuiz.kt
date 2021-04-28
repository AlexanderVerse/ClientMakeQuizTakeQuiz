package com.example.makequiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MakeQuiz : AppCompatActivity()
{
    private var questionText: EditText? = null
    private var answerText: EditText? = null
    private var questionAnswer: TextView? = null
    private var listQuestionAnswer: String? = null
    private var arrayQuestion: ArrayList<String>? = null
    private var arrayAnswer: ArrayList<String>? = null
    private var globalData: GlobalData? = null


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
        globalData = GlobalData()
        
        
    }




    fun addQuestionAnswer(view: View)
    {
        if(questionText?.text.toString() == "" || answerText?.text.toString() == "")
        {
            Toast.makeText(
                this,
                "La pregunta-respuesta no puede ser agregada ya que están vacios los campos",
                Toast.LENGTH_LONG
            ).show()
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
                Toast.makeText(
                    this, "Esta pregunta ya se encuentra en la lista de preguntas y " +
                            "respuestas, favor de ingresar otra", Toast.LENGTH_LONG
                ).show()
                return
            }
        }
        arrayAnswer?.add(answerText?.text.toString())
        arrayQuestion?.add(questionText?.text.toString().replace("?", "").replace("¿", ""))
        listQuestionAnswer = listQuestionAnswer + arrayQuestion?.size +"- ¿<font color=#DD3220>" +
        questionText?.text+
                "?: </font><font color=#36B536>" + answerText?.text + "</font><br>"
        questionAnswer?.text = Html.fromHtml(listQuestionAnswer)
        questionText?.setText("")
        answerText?.setText("")
    }

    fun saveDataToServer(view: View)
    {
        for(element in arrayAnswer!!)
        {
            System.out.println("Answer-->$element<--")
        }
        for(element in arrayQuestion!!)
        {
            System.out.println("Question-->$element<--")
        }
        val context: Context = this
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("question", arrayQuestion?.toString())
        editor.putString("answer", arrayAnswer?.toString())
        editor.apply()
        Toast.makeText(this, "Los datos han sido enviados al servidor", Toast.LENGTH_LONG).show()
    }

    fun removeItemListQA(view: View)
    {

    }
}