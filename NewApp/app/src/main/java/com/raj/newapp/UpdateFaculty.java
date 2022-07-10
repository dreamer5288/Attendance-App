package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class UpdateFaculty extends AppCompatActivity {

    Button update;
    EditText faculty_Name;
    EditText subject_Name;
    Button delete;
    String faculty,teachSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_faculty);

        faculty_Name = findViewById(R.id.facultyname_edt);
        subject_Name = findViewById(R.id.subjectName_edt);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);


        Intent intent = getIntent();
        faculty = intent.getStringExtra("FacultyName");
        teachSubject = intent.getStringExtra("FacultySubject");

        faculty_Name.setText(faculty);
        subject_Name.setText(teachSubject);

        /*
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db = new DbHelper(getApplicationContext());
                String fName=faculty_Name.getText().toString();
                String fSName=subject_Name.getText().toString();
                db.updateFacultyName(fName,fSName);
                db.updateFacultySubject(fName,fSName);
            }
        });

         */

    }
}