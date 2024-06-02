package com.example.acadia.activity.student

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityCommunityBinding
import com.example.acadia.databinding.ActivityJoinNewClassroomBinding

class Community : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}