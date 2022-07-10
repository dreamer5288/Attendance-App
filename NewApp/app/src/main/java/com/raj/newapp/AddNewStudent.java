package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudent extends Activity {
    Button submit;
    EditText etStudentName;
    EditText etStudentFatherName;
    EditText etStudentSubjectName;
    EditText etStudentContact;
    EditText etStudentGender;
    String subject;
    String sTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_new_student);

        etStudentName = findViewById(R.id.studentname_edt);
        etStudentGender = findViewById(R.id.studentGender_edt);
        etStudentFatherName = findViewById(R.id.fathernameS_edt);
        etStudentContact = findViewById(R.id.contactS_edt);
        etStudentSubjectName = findViewById(R.id.subjectS_edt);

        Intent intent = getIntent();
        subject = intent.getStringExtra("sSubject");
        sTeacher = intent.getStringExtra("sTeacher");
        etStudentSubjectName.setText(subject);

        submit = findViewById(R.id.addStudentbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String studentName = etStudentName.getText().toString();
                String fatherName = etStudentFatherName.getText().toString();
                String studentGender = etStudentGender.getText().toString();
                String studentContact = etStudentContact.getText().toString();

                    if(TextUtils.isEmpty(studentName)){
                        etStudentName.setError("Please Enter Student Name");
                        Toast.makeText(AddNewStudent.this, "Please Enter Student Name", Toast.LENGTH_SHORT).show();


                    }else  if(TextUtils.isEmpty(fatherName)){
                        etStudentFatherName.setError("Please Enter Father Name");
                        Toast.makeText(AddNewStudent.this, "Please Enter Father Name", Toast.LENGTH_SHORT).show();

                    }else  if(TextUtils.isEmpty(studentGender)){
                        etStudentGender.setError("Please Enter Gender");
                        Toast.makeText(AddNewStudent.this, "Please Enter Gender", Toast.LENGTH_SHORT).show();

                    }else  if(TextUtils.isEmpty(studentContact)){
                        etStudentContact.setError("Please Enter Student Name");

                        Toast.makeText(AddNewStudent.this, "Please Enter Student Name", Toast.LENGTH_SHORT).show();
                    }else {

                        String studentSubject = etStudentSubjectName.getText().toString();
                        StudentTable newstudent = new StudentTable(etStudentName.getText().toString(), etStudentSubjectName.getText().toString(), etStudentGender.getText().toString(),etStudentFatherName.getText().toString(),etStudentContact.getText().toString());
                        DbHelper dbhelper = new DbHelper(AddNewStudent.this);

                        int count = dbhelper.countStudent(studentName,studentSubject);
                        if(count == 0) {
                            int status = dbhelper.addStudent(studentName, studentSubject, studentGender, etStudentFatherName.getText().toString(), etStudentContact.getText().toString());
                            if (status == -1) {
                                Toast.makeText(AddNewStudent.this, "Insert Error. Try Again", Toast.LENGTH_SHORT).show();
                            } else if (status == 0) {
                                Toast.makeText(AddNewStudent.this, " Student Name already present with in this Subject", Toast.LENGTH_SHORT).show();
                            } else if (status == 1) {
                                Toast.makeText(AddNewStudent.this, " Student added Successfully", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }else{
                            Toast.makeText(AddNewStudent.this, "Student already Exist with this Name \nPlease Select a Different Student Name", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }
}