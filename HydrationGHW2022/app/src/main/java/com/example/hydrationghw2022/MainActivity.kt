package com.example.hydrationghw2022

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.security.AccessController.getContext
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared preferences
        val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
        var waterCount = sharedPreferences.getString("WATERCOUNT", "0")
        var editor = sharedPreferences.edit()


        // start of code
        val addButton: Button = findViewById(R.id.addWater_Button)
        val waterCount_Text: TextView = findViewById(R.id.waterCount_TextView)
        waterCount_Text.text = waterCount
        addButton.setOnClickListener {
            waterCount_Text.text = (Integer.parseInt(waterCount_Text.text as String) + 1).toString()
            editor.putString("WATERCOUNT", waterCount_Text.text as String)
            editor.commit()
            if (Integer.parseInt(waterCount_Text.text as String) > 8){
                //toast stuff
                var toast: Toast = Toast.makeText(this, "Congratz! You did it!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
//                var view: View? = toast.getView()
//                if (view != null) {
//                    view.setBackgroundResource(R.drawable.ic_toast)
//                }
                toast.show()
            }
        }

        //time
        val currentDate = LocalDate.now().toString()
        val date_TextView: TextView = findViewById(R.id.date_TextView)
        var pastTime: String = sharedPreferences.getString("PASTTIME", "2022-04-04").toString()
        if (pastTime != currentDate) {
            editor.putString("PASTTIME", currentDate)
            editor.putString("WATERCOUNT", "0")
            editor.commit()
            waterCount_Text.text = "0"
        }
        date_TextView.text = currentDate


        //clear count
        val clearButton: Button = findViewById(R.id.clear_text)
        clearButton.setOnClickListener {
            waterCount_Text.text = "0"
            editor.putString("WATERCOUNT", "0")
            editor.commit()
        }
    }
}