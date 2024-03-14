package com.example.acadia.activity.student

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityQuizBinding
import com.example.acadia.databinding.ActivityQuizListBinding

class Quiz : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerQuiz.setOnClickListener{
            val intent= Intent(this, Quiz::class.java)
            startActivity(intent)
            finish()
        }


    }
}