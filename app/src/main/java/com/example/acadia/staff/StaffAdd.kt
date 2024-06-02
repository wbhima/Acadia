package com.example.acadia.activity.staff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.R
import com.example.acadia.databinding.ActivityStaffAddBinding

class StaffAdd : AppCompatActivity() {
    private lateinit var binding: ActivityStaffAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add your functionality here, for example:
        binding.btnSubmit.setOnClickListener {
            val name = binding.etStaffName.text.toString()
            val address = binding.etAddress.text.toString()
            val email = binding.editTextTextEmailAddress.text.toString()
            val gender = binding.editTextText3.text.toString()
            val password = binding.etPassword.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val designation = binding.editTextText4.text.toString()
            val field = binding.etField.text.toString()

            // Perform actions such as form validation or submission
        }
    }
}
