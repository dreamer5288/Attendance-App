package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StatisticsActivity extends AppCompatActivity {

    TextView editTextStudName;
    TextView editTextStudSub;
    TextView editTextGender;
    TextView editTextPresentCount;
    TextView editTextAbsentCount;
    TextView editTextPercentage;
    TextView editTextFatherName;
    TextView editTextContact;

    List<StudentTable> studentTableArrayList = new ArrayList<>();

    String student,subject;
    int present,absent;
    String percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistics);

        editTextStudName =findViewById(R.id.studName_et);
        editTextFatherName =findViewById(R.id.stFatherName_et);
        editTextStudSub =findViewById(R.id.subName_et);
        editTextGender =findViewById(R.id.genderName_et);
        editTextContact =findViewById(R.id.studContact_et);
        editTextPresentCount =findViewById(R.id.presentCount_et);
        editTextAbsentCount =findViewById(R.id.absentCount_et);
        editTextPercentage =findViewById(R.id.percent_et);


        Intent intent = getIntent();
        student = intent.getStringExtra("sNameForStatistics");
        percent = intent.getStringExtra("Percentage");
        present = intent.getIntExtra("presentCount",1);
        absent = intent.getIntExtra("absentCount",1);
        subject = GlobalVariable.SubjectName;


        editTextStudName.setText(student);
        editTextStudSub.setText(subject);
        editTextPresentCount.setText(""+present);
        editTextAbsentCount.setText(""+absent);
        editTextPercentage.setText(percent +" %");

        DbHelper db = new DbHelper(getApplicationContext());
        final List<String> studentTableList = new ArrayList<>();
        studentTableArrayList = db.getAllStudent();

        for(StudentTable studentTable : studentTableArrayList)
        {
            String studentSubject =studentTable.getStudent_subject();
            String studentName = studentTable.getStudent_name();
            int studentId =studentTable.getSid();
            if(studentName.equals(student)){
                if(studentSubject.equals(subject)){
                    String gender = studentTable.getStudent_gender();
                    String contact = studentTable.getStudent_contact();
                    String fatherName = studentTable.getFather_name();
                    DbHelper database = new DbHelper(getApplicationContext());

                    editTextGender.setText(gender);
                   editTextFatherName.setText(fatherName);
                   editTextContact.setText(contact);
                  //  Toast.makeText(this, "Father Name: "+fatherName+" Contact: "+contact, Toast.LENGTH_SHORT).show();
                }

            }

        }

    }
}