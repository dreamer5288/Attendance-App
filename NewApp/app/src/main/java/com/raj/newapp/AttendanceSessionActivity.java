package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceSessionActivity extends AppCompatActivity {

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    int facultyId;
    String teacherName;
    String subjectName;


    TextView textViewTeacher;
    TextView textViewSubject;
    EditText editTextDate;
    ImageButton imageButtonCal;
    CardView viewAttendance;
    CardView updateAttendance;
    CardView addAttendance;
    CardView totalAttendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_attendance_session);

        Intent intent = getIntent();
        teacherName = intent.getStringExtra("teacher");
        subjectName = intent.getStringExtra("subject");
        facultyId = intent.getExtras().getInt("teacherId");

        textViewSubject = findViewById(R.id.teacherName_tv);
        textViewTeacher = findViewById(R.id.subjectName_tv);
        editTextDate = findViewById(R.id.date_edt);
       // addAttendance = findViewById(R.id.addAttendance_btn);
        addAttendance = findViewById(R.id.card_addAttendance);
        viewAttendance = findViewById(R.id.card_viewAttendance);
        updateAttendance = findViewById(R.id.card_updateAttendance);
        totalAttendance = findViewById(R.id.card_totalAttendance);

        imageButtonCal = findViewById(R.id.calender_img_btn);
        //simpleDatePicker = findViewById(R.id.simpleDatePicker);

        textViewSubject.setText("Teacher : "+teacherName);
        textViewTeacher.setText("Subject : " +subjectName);

        GlobalVariable.SubjectName = subjectName;

        Toast.makeText(AttendanceSessionActivity.this, "Please select Date First", Toast.LENGTH_SHORT).show();

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        imageButtonCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);
            }
        });
        String date = editTextDate.getText().toString();

            addAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GlobalVariable.SubjectName = subjectName;
                    GlobalVariable.Date = editTextDate.getText().toString();
                    DbHelper db = new DbHelper(getApplicationContext());

                    String teacherName = textViewSubject.getText().toString();
                    GlobalVariable.FacultyName = teacherName;


                        Intent i = new Intent(getApplicationContext(), AddAttendance.class);
                        startActivity(i);
                }
            });

            viewAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int asFacultyId = facultyId;
                    String faculty = teacherName;
                    String Subject = subjectName;
                    String date = editTextDate.getText().toString();

                    GlobalVariable.Date = date;
                    GlobalVariable.SubjectName = subjectName;

                    Intent i = new Intent(getApplicationContext(), ViewAttendance.class);
                    startActivity(i);
                }
            });

            updateAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int asFacultyId = facultyId;
                    String faculty = teacherName;
                    String Subject = subjectName;
                    String date = editTextDate.getText().toString();

                    GlobalVariable.Date = date;
                    GlobalVariable.SubjectName = subjectName;

                    Intent i = new Intent(getApplicationContext(), UpdateAttendance.class);
                    startActivity(i);

                }
            });

        totalAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVariable.SubjectName = subjectName;
                GlobalVariable.Date=editTextDate.getText().toString();
                DbHelper db = new DbHelper(getApplicationContext());


                String teacherName = textViewSubject.getText().toString();
                GlobalVariable.FacultyName=teacherName;


                Intent i = new Intent(getApplicationContext(), TotalAttendance.class);

                startActivity(i);
            }
        });


    }

        @Override
        @Deprecated
        protected Dialog onCreateDialog(int id) {
            return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                editTextDate.setText(selectedDay + "." + (selectedMonth + 1) + "."
                        + selectedYear);
            }
        };
}