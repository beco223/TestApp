package com.example.testapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.testapp.MainActivity4
import com.example.testapp.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
     var autha : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initialization()
    }

    private fun initialization(){
        val mail = findViewById<EditText>(R.id.mail)
        val pass = findViewById<EditText>(R.id.pass)
        val log = findViewById<Button>(R.id.login)
        val creat = findViewById<TextView>(R.id.createaccount)
        autha = FirebaseAuth.getInstance()

       creat.setOnClickListener(View.OnClickListener {
           var intent = Intent(this, SignUp::class.java)
           startActivity(intent)
           finish()
       })

       log.setOnClickListener(View.OnClickListener {

           if (mail.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()){
               login(mail.text.toString(),pass.text.toString())
           }else{
               Toast.makeText(this,"plz enter a valid values",Toast.LENGTH_LONG).show()
           }
       })
    }

   private fun login(email:String ,password:String){
       autha!!.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener{
               if (it.isSuccessful){
                   Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
                   var intent = Intent(this, MainActivity4::class.java)
                   startActivity(intent)
                   finish()
               }else{
                   Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
               }
           }
   }
}