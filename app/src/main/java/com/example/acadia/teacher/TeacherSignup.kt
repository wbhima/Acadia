package com.example.acadia.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.acadia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class TeacherSignup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_signup)

        // Initialize Firebase Authentication and Realtime Database instances
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val nameEditText = findViewById<EditText>(R.id.teachsignname)
        val addressEditText = findViewById<EditText>(R.id.teachsignadd)
        val phoneEditText = findViewById<EditText>(R.id.teachsignphone)
        val positionEditText = findViewById<EditText>(R.id.teachsignposi)
        val genderEditText = findViewById<EditText>(R.id.teachsigngender)
        val emailEditText = findViewById<EditText>(R.id.teachsignemail)
        val passwordEditText = findViewById<EditText>(R.id.teachsignpassword)
        val button3 = findViewById<Button>(R.id.button3)

        button3.setOnClickListener {
            val name = nameEditText.text.toString()
            val address = addressEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val position = positionEditText.text.toString()
            val gender = genderEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()
                && position.isNotEmpty() && gender.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            ) {
                signUpWithEmailAndPassword(name, address, phone, position, gender, email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUpWithEmailAndPassword(
        name: String,
        address: String,
        phone: String,
        position: String,
        gender: String,
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // Push user details to the Realtime Database under "teacherdata"
                    user?.uid?.let { uid ->
                        val userRef = database.reference.child("teacherdata").child(uid)
                        val userData = HashMap<String, Any>()
                        userData["name"] = name
                        userData["address"] = address
                        userData["phone"] = phone
                        userData["position"] = position
                        userData["gender"] = gender
                        userData["email"] = email
                        // Push user data to the database
                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "User signed up successfully and data added to database.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@TeacherSignup, MainActivityTeacher::class.java))

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