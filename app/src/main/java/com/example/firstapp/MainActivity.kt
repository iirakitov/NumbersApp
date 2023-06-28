package com.example.firstapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.numbersapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private val keyForNumber: String = "key_for_number"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("myFile", MODE_PRIVATE)

        Log.d("MyTag", "OnCreate works")

        val myButton = findViewById<Button>(R.id.myButton)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonReset = findViewById<Button>(R.id.resetButton)
        val myText = findViewById<TextView>(R.id.myTextView)

        var counter = getNumber()
        myText.text = counter.toString()


        myButton.setOnClickListener {
            Log.d("MyTag", "Click")
            counter += 1
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }

        buttonMinus.setOnClickListener {

            if (counter > 0) {
                counter -= 1
            }

            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }

        buttonReset.setOnClickListener {
            counter = 0
            saveNumber(counter)
            val numberAsString = counter.toString()
            myText.text = numberAsString
        }
    }



    private fun saveNumber(number: Int) {
        val editor = sharedPref.edit()
        editor.putInt(keyForNumber, number)
        editor.commit()
    }

    private fun getNumber(): Int {
        return sharedPref.getInt(keyForNumber, 0)
    }
}