package com.example.acadia.activity.student

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityJoinNewClassroomBinding
import com.example.acadia.databinding.ActivityQuizBinding

class JoinNewClassroom : AppCompatActivity() {
    private lateinit var binding: ActivityJoinNewClassroomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJoinNewClassroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}