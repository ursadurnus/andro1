package com.example.volumecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private var gender: String="Laki-laki"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)

        buttonCalculate.setOnClickListener{
            calculateBMI(editTextWeight, editTextHeight, radioGroupGender, textViewResult)
        }
    }

    private fun calculateBMI(
        editTextHeight: EditText,
        editTextWeight: EditText,
        radioGroupGender: RadioGroup,
        textViewResult: TextView
    ){
        val height = editTextHeight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()
        val selectedGenderId = radioGroupGender.checkedRadioButtonId

        gender = when (selectedGenderId){
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        val bmi = when (gender){
            "Laki-laki" -> weight / ((height/100) * (height/100))
            "Perempuan" -> weight / ((height/100) * (height/100) * 0.9 )
                else -> 0.0
        }

        val result = when{
            bmi <18.5 -> "Berat badan kurang"
            bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
            bmi >= 25 && bmi < 29.9 -> "Berat badan berlebih"
            else -> "Obesitas"
        }

        textViewResult.text = "BMI: %.2f\n$result".format(bmi)
    }
}