package com.example.acadia.staff

import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.acadia.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.Executor

class StaffSignup : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var executor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_signup)

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Initialize Executor
        executor = ContextCompat.getMainExecutor(this)

        // Initialize BiometricPrompt
//        biometricPrompt = BiometricPrompt(
//            this,
//            executor,
//            object : BiometricPrompt.AuthenticationCallback() {
//                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    markAttendance("ClassName", "StudentName")
//                }
//            })
    }

    private fun markAttendance(className: String, studentName: String) {
        // Add a document to the attendance collection under the specified class name
        val attendanceRef = firestore.collection("attendance").document(className)
            .collection("students").document(studentName)

        // Mark attendance as present
        val attendanceData = hashMapOf(
            "name" to studentName,
            "status" to "present"
        )

        attendanceRef.set(attendanceData)
            .addOnSuccessListener {
                // Attendance marked successfully
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }

//    private fun authenticateWithFingerprint() {
//        val promptInfo = BiometricPrompt.PromptInfo.Builder()
//            .setTitle("Fingerprint Authentication")
//            .setSubtitle("Touch the fingerprint sensor to mark attendance")
//            .setNegativeButtonText("Cancel")
//            .build()
//
//        biometricPrompt.authenticate(promptInfo)
//    }
}
