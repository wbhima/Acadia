package com.example.acadia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Authentication and Realtime Database instances
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val emailEditText = findViewById<EditText>(R.id.logemail)
        val passwordEditText = findViewById<EditText>(R.id.logpassword)
        val submitButton = findViewById<Button>(R.id.button)
        val logSignTextView = findViewById<TextView>(R.id.logsign)
logSignTextView.setOnClickListener {
    startActivity(Intent(this@Login, Signupmain::class.java))

}
        submitButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if (user != null) {
                        // Retrieve the user's position from the database
                        val uid = user.uid

                        val userRef = database.reference.child("clgmembers").child(email)
                        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    val position = dataSnapshot.child("position").value.toString()
                                    // Navigate to the appropriate activity based on the position
                                    when (position) {
                                        "student" -> {
                                            // Navigate to StudentActivity
                                            Toast.makeText(this@Login, "Welcome, Student!", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this@Login, MainActivityStudent::class.java))

                                        }
                                        "teacher" -> {
                                            // Navigate to TeacherActivity
                                            Toast.makeText(this@Login, "Welcome, Teacher!", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this@Login, MainActivityTeacher::class.java))
                                        }
                                        "staff" -> {
                                            // Navigate to StaffActivity
                                            Toast.makeText(this@Login, "Welcome, Staff!", Toast.LENGTH_SHORT).show()
                                             startActivity(Intent(this@Login, MainActivityStaff::class.java))
                                        }
                                        else -> {
                                            // Handle unknown position
                                            Toast.makeText(this@Login, "Unknown position", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                } else {
                                    // Handle case where position data is not found in the database
                                    Toast.makeText(this@Login, "Position data not found", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle database error
                                Toast.makeText(
                                    this@Login,
                                    "Database error: ${databaseError.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    } else {
                        Toast.makeText(this@Login, "User not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed. " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
