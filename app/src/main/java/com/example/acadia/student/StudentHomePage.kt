package com.example.acadia.activity.student

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityLoginBinding
import com.example.acadia.databinding.ActivityStaffHomePageBinding
import com.example.acadia.databinding.ActivityStudentHomePageBinding

class StudentHomePage : AppCompatActivity() {

    private lateinit var binding:ActivityStudentHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener{
            val intent=Intent(this, StudentProfile::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnClassroom.setOnClickListener{
            val intent=Intent(this,Classroom::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnCommunity.setOnClickListener{
            val intent=Intent(this,Community::class.java)
            startActivity(intent)
            finish()
        }



    }
}