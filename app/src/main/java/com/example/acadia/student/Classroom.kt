package com.example.acadia.activity.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.databinding.ActivityClassroomBinding

class Classroom : AppCompatActivity() {
    private lateinit var binding: ActivityClassroomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityClassroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnClassroomJoin.setOnClickListener{
            val intent= Intent(this, JoinNewClassroom::class.java)
            startActivity(intent)
            finish()
        }
        binding.recyclerClassroomList.setOnClickListener{
            val intent=Intent(this, ClassroomN::class.java)
            startActivity(intent)
            finish()
        }

    }
}