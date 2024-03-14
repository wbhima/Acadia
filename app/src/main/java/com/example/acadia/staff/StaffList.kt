package com.example.acadia.activity.staff

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityStaffListBinding

class StaffList : AppCompatActivity() {
    private lateinit var binding:ActivityStaffListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityStaffListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerStaffList.setOnClickListener{
            val intent= Intent(this, StaffData::class.java)
            startActivity(intent)
            finish()
        }
    }
}