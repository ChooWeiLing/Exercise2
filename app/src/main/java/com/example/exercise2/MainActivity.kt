package com.example.exercise2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener(){
            try {
                val weight:Double = editTextWeight.text.toString().toDouble()
                val height:Double = editTextHeight.text.toString().toDouble()

                val bmi = btnCalculateClick(height,weight,it)

                if(bmi < 18.5){
                imageViewProfile.setImageResource(R.drawable.under)
                textViewBMI.setText("BMI: " + String.format("%.2f",bmi) + "(Underweight)")
                }else if(bmi >= 18.5 && bmi <= 24.9) {
                imageViewProfile.setImageResource(R.drawable.normal)
                textViewBMI.setText("BMI: " + String.format("%.2f", bmi) + "(Normal)")
                }else if(bmi >= 25) {
                imageViewProfile.setImageResource(R.drawable.over)
                textViewBMI.setText("BMI: " + String.format("%.2f", bmi) + "(Overweight)")
                }
            }catch (ex: Exception){
                val toast:Toast = Toast.makeText(applicationContext,"Please input value", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0,0)
                toast.show()
            }
        }

        btnReset.setOnClickListener(){
            editTextHeight.setText("")
            editTextWeight.setText("")
            textViewBMI.setText("")
            imageViewProfile.setImageResource(R.drawable.empty)
            editTextWeight.requestFocus()
        }
    }

    private fun btnCalculateClick(height:Double, weight:Double,view:View):Double{
        val bmiResult:Double = weight / (height * height)

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        return bmiResult
    }
}
