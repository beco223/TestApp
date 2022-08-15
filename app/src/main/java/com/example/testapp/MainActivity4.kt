package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testapp.databinding.ActivityMain4Binding
import com.example.testapp.fragment.Favairote

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding : ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFagment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
               R.id.home -> replaceFragment(HomeFagment())
               R.id.favorit -> replaceFragment(Favairote())
               R.id.profile -> replaceFragment(ProfileFramgnet())

               else -> {

               }
           }
            true
        }

    }
    private fun replaceFragment(f:Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmenttranaction = fragmentManager.beginTransaction()
        fragmenttranaction.replace(R.id.framelayout,f)
        fragmenttranaction.commit()

    }
}