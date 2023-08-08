package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvResultNumber : TextView
    private lateinit var tvResultTxt : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResultNumber = findViewById<TextView>(R.id.tvResultNumber)
        tvResultTxt = findViewById<TextView>(R.id.tvResultTxt)
        val edtWeight = findViewById<EditText>(R.id.edtWeight)
        val edtHeight = findViewById<EditText>(R.id.edtHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener(View.OnClickListener {

            if (validation(edtWeight.text.toString()) && validation(edtHeight.text.toString())){
                val weight = edtWeight.text.toString().toFloat()
                val height = edtHeight.text.toString().toFloat()/100
                val bmi = weight/(height*height)
                val bmi2digits = String.format("%.2f",bmi).toFloat()
                showBmi(bmi2digits)
            }
            else {
                Toast.makeText(this, "pleas enter valid inputs :)", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun validation (input : String) : Boolean{
        if (input.equals(null) || input == "")
            return false
        if (input.toFloat() < 0)
            return false
        return true
    }

    private fun showBmi (bmi : Float){
        tvResultNumber.text = bmi.toString()
        var color = 0
        var text = ""
        when {
            bmi < 18.50 -> {
                text = "You are underweight"
                color = R.color.underweight
            }
            bmi in 18.50 .. 24.99 -> {
                text = "You are healthy"
                color = R.color.healthy
            }

            bmi in 25.00 .. 29.99 -> {
                text = "You are overweight"
                color = R.color.overweight
            }

            bmi in 18.50 .. 24.99 -> {
                text = "You are fat"
                color = R.color.obese
            }
        }
        tvResultTxt.text = text
        tvResultTxt.setTextColor(ContextCompat.getColor(this,color))


    }
}