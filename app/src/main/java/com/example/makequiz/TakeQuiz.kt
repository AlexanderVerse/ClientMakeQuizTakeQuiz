package com.example.makequiz

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array

class TakeQuiz : AppCompatActivity()
{
    private var answerText: EditText? = null
    private var questionText: TextView? = null
    private var buttonNextQuestion: Button? = null
    private val TAG_FRAGMENT = "TAG_FRAGMENT"
    private var arrayQuestion = emptyArray<String>()
    private var arrayAnswer = emptyArray<String>()
    private var arrayAnswerUser  = emptyArray<String>()
    private var question: String? = null
    private var answer: String? = null
    private var totalQuestion = 0
    private var indexQuestion = 0
    private var indexAnswer = 0
    private var globalData: GlobalData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)
        questionText = findViewById(R.id.questionServer)
        answerText = findViewById(R.id.answerServer)
        buttonNextQuestion = findViewById(R.id.buttonNext)
        buttonNextQuestion?.text = "Next"
        questionText?.requestFocus()
        val context = this
        val userData: SharedPreferences = context.getSharedPreferences("UserData", MODE_PRIVATE)
        Log.i(TAG_FRAGMENT, userData.toString());
        question = userData.getString("question", null)?.replace("[", "")?.replace("]", "")
        answer = userData.getString("answer", null)?.replace("[", "")?.replace("]", "")?.replace(" ", "")
        System.out.println("--->Question:${question}")
        System.out.println("--->Answer:${answer}")
        totalQuestion = question?.split(",")?.size!!
        System.out.println("--->Total: $totalQuestion")
        questionText?.text = question!!.split(",")[indexQuestion]
        arrayAnswerUser = kotlin.Array(totalQuestion) { indice -> (indice + 1).toString() }
    }

    fun nextQuestion(view: View)
    {
        arrayAnswerUser[indexAnswer] = answerText?.text.toString().replace(" ", "")
        ++indexAnswer
        if(indexQuestion < (totalQuestion - 1))
        {
            ++indexQuestion
            questionText?.text = question!!.split(",")[indexQuestion]
        }
        else
        {
            checkQuestion()
        }
        answerText?.setText("")
    }

    private fun checkQuestion()
    {
        indexAnswer = 0
        indexQuestion = 0
        var answerCorrect = 0
        for(element in arrayAnswerUser)
        {
            System.out.println("----->element:$element==${answer!!.split(",")[indexQuestion]}<----")
            if(element == answer!!.split(",")[indexQuestion])
            {
                ++answerCorrect
            }
            ++indexQuestion
        }
        indexQuestion = 0
        showMessage(answerCorrect, totalQuestion)
        answerCorrect = 0
    }

    private fun showMessage(correctAnswer: Int, totalQuestion: Int)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("El quiz ha finalizado")
        builder.setMessage("Tuviste $correctAnswer respuestas correctas de $totalQuestion")
            .setPositiveButton(
                "Ok"
            ) { dialog: DialogInterface?, id: Int ->

            }.setOnDismissListener { dialogInterface: DialogInterface? ->

            }
        builder.create().show()
        val intent: Intent = Intent(this, MainActivity::class.java).apply {

        }
//        finish()
//        startActivity(intent)
    }

}