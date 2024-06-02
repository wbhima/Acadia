package com.example.acadia.activity.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.databinding.ActivityClassroomListBinding

class ClassroomN : AppCompatActivity() {
    private lateinit var binding:ActivityClassroomListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityClassroomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnClassroomAttendenceReport.setOnClickListener{
            val intent= Intent(this, AttendenceReport::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnClassroomQuiz.setOnClickListener{
            val intent=Intent(this, QuizList::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnClassroomChat.setOnClickListener{
            val intent=Intent(this, ClassroomChat::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnAssignment.setOnClickListener{
            val intent=Intent(this, AssignmentList::class.java)
            startActivity(intent)
            finish()
        }

    }
}