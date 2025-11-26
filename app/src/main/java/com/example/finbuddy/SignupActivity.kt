package com.example.finbuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        val register_button: Button = findViewById(R.id.register_button)
        val create_name: EditText = findViewById(R.id.create_name)
        val create_email: EditText = findViewById(R.id.create_email)
        val create_phone: EditText = findViewById(R.id.create_phone)
        val create_dob: EditText = findViewById(R.id.create_date)
        val rgGender: RadioGroup = findViewById(R.id.rgGender)
        val cbTerms: CheckBox = findViewById(R.id.cbTerms)


        register_button.setOnClickListener {
            val name = create_name.text.toString()
            val email = create_email.text.toString()
            val phoneStr = create_phone.text.toString()
            val phoneNumber = phoneStr.toLongOrNull()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val isTermsAccepted = cbTerms.isChecked
            if (name.isBlank() || email.isBlank()) {
                Toast.makeText(this, "Please fill all details!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phoneNumber == null || phoneStr.length != 10) {
                create_phone.error = "Enter a valid 10-digit phone number"
                return@setOnClickListener
            }

            if (selectedGenderId == -1) {
                Toast.makeText(this, "Please select your gender",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isTermsAccepted) {
                Toast.makeText(
                    this, "Please accept the terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Account Created Succesfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}