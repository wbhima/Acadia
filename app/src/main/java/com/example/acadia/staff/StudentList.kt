package com.example.acadia.activity.staff

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.databinding.ActivityStudentListBinding

class StudentList : AppCompatActivity() {
    private lateinit var binding:ActivityStudentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStudentListBinding.inflate((layoutInflater))
        setContentView(binding.root)

        binding.recyclerStudentList.setOnClickListener{
            val intent= Intent(this, StudentData::class.java)
            startActivity(intent)
            finish()
        }

    }
}