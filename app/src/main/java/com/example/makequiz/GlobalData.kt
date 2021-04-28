package com.example.makequiz

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class GlobalData {

    var urlServer: String = "http://localhost:8080/"
    var urlSaveData: String = "http://localhost:8080/add"

    fun showMessage(correctAnswer: Int, totalQuestion: Int, activity: Activity)
    {
        System.out.println("-----> Estoy dentro de SHOWMESSAGE")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("El quiz ha finalizado")
        builder.setMessage("Tuviste $correctAnswer respuestas correctas  de $totalQuestion")
            .setPositiveButton(
                "Ok"
            ) { dialog: DialogInterface?, id: Int ->
                
            }.setOnDismissListener { dialogInterface: DialogInterface? ->
                
            }
        builder.create().show()
    }
}