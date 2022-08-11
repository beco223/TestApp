package com.example.testapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.testapp.R
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    var mauth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        initiation()
    }


    private fun initiation(){
        val rmail = findViewById<EditText>(R.id.rmail)
        val rpass = findViewById<EditText>(R.id.rpass)
        val rconfirmpass = findViewById<EditText>(R.id.confirmpass)
        val regist = findViewById<Button>(R.id.register)
        val rcreat = findViewById<TextView>(R.id.gotologin)


        mauth = FirebaseAuth.getInstance()


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
}