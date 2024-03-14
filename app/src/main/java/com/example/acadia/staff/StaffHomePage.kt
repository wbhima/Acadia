package com.example.acadia.activity.staff

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.acadia.R
import com.example.acadia.databinding.ActivityStaffHomePageBinding

class StaffHomePage : AppCompatActivity() {

    private lateinit var binding:ActivityStaffHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStaffHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStaffProfile.setOnClickListener{
            val intent= Intent(this, StaffProfile::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnStaffList.setOnClickListener{
            val intent= Intent(this, StaffList::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnStudentList.setOnClickListener{
            val intent= Intent(this, StudentList::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnAddStaff.setOnClickListener{
            val intent= Intent(this, StaffAdd::class.java)
            startActivity(intent)
            finish()
        }

    }
}