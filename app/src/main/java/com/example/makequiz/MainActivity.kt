package com.example.makequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showMakeQuiz(view: View)
    {
      val intent: Intent = Intent(this, MakeQuiz::class.java).apply {
          
      }
        startActivity(intent)
        
    }

    fun showTakeQuiz(view: View)
    {
        val intent: Intent = Intent(this, TakeQuiz::class.java).apply {
            
        }
        startActivity(intent)
    }
}