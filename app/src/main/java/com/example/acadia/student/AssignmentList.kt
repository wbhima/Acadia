package com.example.acadia.activity.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.databinding.ActivityAssignmentListBinding

class AssignmentList : AppCompatActivity() {
    private lateinit var binding:ActivityAssignmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAssignmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerAssignmentList.setOnClickListener{
            val intent= Intent(this, AssignmentUpload::class.java)
            startActivity(intent)
            finish()
        }

    }
}