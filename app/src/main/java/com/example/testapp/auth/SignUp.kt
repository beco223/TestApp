package com.example.testapp.auth

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMain3Binding
import com.example.testapp.databinding.ActivityMain4Binding
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class SignUp : AppCompatActivity() {
    var mauth : FirebaseAuth? = null
    private lateinit var binding :ActivityMain3Binding
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initiation()
    }


    private fun initiation(){
        val rmail = findViewById<EditText>(R.id.rmail)
        val rpass = findViewById<EditText>(R.id.rpass)
        val rconfirmpass = findViewById<EditText>(R.id.confirmpass)
        val regist = findViewById<Button>(R.id.register)
        val rcreat = findViewById<TextView>(R.id.gotologin)
        val calender = findViewById<TextView>(R.id.birthdate)


        mauth = FirebaseAuth.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        calender.setOnClickListener(View.OnClickListener {
            DatePickerDialog(this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        })
        rcreat.setOnClickListener(View.OnClickListener {
            var intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        })

        regist.setOnClickListener(View.OnClickListener {
            if (rmail.text.isNotEmpty() && rpass.text.isNotEmpty() && rconfirmpass.text.isNotEmpty()){
               signUp(rmail.text.toString(),rpass.text.toString())
                rmail.setText("")
                rpass.setText("")
                rconfirmpass.setText("")
            }else{
                Toast.makeText(this,"plz enter data",Toast.LENGTH_LONG).show()
            }
        })

    }
    private fun signUp(register_email : String, register_password: String){
        mauth!!.createUserWithEmailAndPassword(register_email,register_password)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this,"done",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.birthdate.text = sdf.format(cal.getTime())

    }
}