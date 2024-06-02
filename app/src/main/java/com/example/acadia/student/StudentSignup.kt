package com.example.acadia.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.acadia.R
import com.example.acadia.activity.student.StudentHomePage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class StudentSignup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_signup)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val phoneEditText = findViewById<EditText>(R.id.phone_edit_text)
        val genderEditText = findViewById<EditText>(R.id.gender_edit_text)
        val signUpButton = findViewById<Button>(R.id.sign_up_button)
        val addressEditText = findViewById<EditText>(R.id.address_edit_text)
        val positionEditText = findViewById<EditText>(R.id.position_edit_text)
        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val departmentEditText = findViewById<EditText>(R.id.department_edit_text)

        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val gender = genderEditText.text.toString()
            val address = addressEditText.text.toString()
            val position = positionEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val department = departmentEditText.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty() && gender.isNotEmpty() && address.isNotEmpty()
                && position.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && department.isNotEmpty()
            ) {
                signUpWithEmailAndPassword(name, phone, gender, address, position, email, password, department)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUpWithEmailAndPassword(
        name: String,
        phone: String,
        gender: String,
        address: String,
        position: String,
        email: String,
        password: String,
        department: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // Push user details to the Realtime Database under "studentdata"
                    user?.uid?.let { uid ->
                        val userRef = database.reference.child("studentdata").child(uid)
                        val userData = HashMap<String, Any>()
                        userData["name"] = name
                        userData["phone"] = phone
                        userData["gender"] = gender
                        userData["address"] = address
                        userData["position"] = position
                        userData["email"] = email
                        userData["department"] = department
                        // Push user data to the database
                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "User signed up successfully and data added to database.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@StudentSignup, StudentHomePage::class.java))

                                // Here you can navigate to the next activity or perform other actions
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Failed to add user data to database: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
