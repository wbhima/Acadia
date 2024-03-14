package com.example.acadia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Signupmain : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupmain)

        // Initialize Firebase Authentication and Realtime Database instances
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val emailEditText = findViewById<EditText>(R.id.signemail)
        val button2 = findViewById<Button>(R.id.button2)
var position="";
        button2.setOnClickListener {
            // Get the email from the EditText
            val email = emailEditText.text.toString()

            // Check if the email is empty
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val database = Firebase.database
            val myRef = database.getReference("clgmembers").child(email).child("position")

            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                     position = snapshot.getValue<String>().toString()

                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
            // Query the Firebase Realtime Database to check if the email exists in "clgmember" node

                        when (position) {
                            "student" -> {
                                // Navigate to StudentActivity
                                Toast.makeText(this@Signupmain, "Welcome, Student!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Signupmain, StudentSignup::class.java))
                            }
                            "teacher" -> {
                                // Navigate to TeacherActivity
                                Toast.makeText(this@Signupmain, "Welcome, Teacher!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Signupmain, TeacherSignup::class.java))
                            }
                            "staff" -> {
                                // Navigate to StaffActivity
                                Toast.makeText(this@Signupmain, "Welcome, Staff!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Signupmain, StaffSignup::class.java))
                            }
                            else -> {
                                // Handle unknown position
                                Toast.makeText(this@Signupmain, "Unknown position", Toast.LENGTH_SHORT).show()
                            }
                        }

        }}}