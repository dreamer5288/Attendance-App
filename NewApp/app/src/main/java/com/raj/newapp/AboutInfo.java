package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutInfo extends AppCompatActivity {

    TextView textViewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_info);

        textViewAbout = findViewById(R.id.about_info);

        textViewAbout.setText("Attendance management system deals with the maintenance of the student's\n" +
                "attendance details. This project starts with entering the details of Teacher with their respective subjects and class. Teacher adds the details of students, and then\n" +
                "teacher takes the attendance of students and of the current date, class and take the attendance of students."+"\nOn submitting, it generates the attendance of the student\n" +
                "present in class.");


    }
}