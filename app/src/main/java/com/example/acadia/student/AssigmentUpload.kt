package com.example.acadia.activity.student

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.acadia.R

class AssignmentUpload : AppCompatActivity() {

    private lateinit var txtClassroomName: TextView
    private lateinit var etAssignmentName: EditText
    private lateinit var btnUpload: Button
    private lateinit var btnUploadAnswer: Button

    private val PICK_PDF_REQUEST_CODE = 1
    private val PICK_ANSWER_REQUEST_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assigment_upload)

        txtClassroomName = findViewById(R.id.txt_classroom_name)
        etAssignmentName = findViewById(R.id.et_assignment_name)
        btnUpload = findViewById(R.id.btn_upload)
        btnUploadAnswer = findViewById(R.id.btn_upload_answer)

        btnUpload.setOnClickListener {
            selectPdfFile()
        }

        btnUploadAnswer.setOnClickListener {
            selectAnswerFile()
        }
    }

    private fun selectPdfFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        startActivityForResult(intent, PICK_PDF_REQUEST_CODE)
    }

    private fun selectAnswerFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, PICK_ANSWER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_PDF_REQUEST_CODE -> {
                    val pdfUri: Uri? = data?.data
                    if (pdfUri != null) {
                        uploadFile(pdfUri, "pdf")
                    }
                }
                PICK_ANSWER_REQUEST_CODE -> {
                    val answerUri: Uri? = data?.data
                    if (answerUri != null) {
                        uploadFile(answerUri, "answer")
                    }
                }
            }
        }
    }

    private fun uploadFile(uri: Uri, type: String) {
        // Handle the file upload logic here
        // This is where you would upload the file to your server or cloud storage
        Toast.makeText(this, "$type file selected: ${uri.path}", Toast.LENGTH_SHORT).show()
    }
}
