package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TotalAttendance extends AppCompatActivity {

    private ListView listView ;
    List<StudentTable> studentTableArrayList;


    String faculty;
    final ArrayList<String> studentList = new ArrayList<String>();
    TextView facultyName;
    TextView subDate;
    String sDate;
    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_total_attendance);

        facultyName = findViewById(R.id.facultyName_tv);
        faculty = GlobalVariable.FacultyName;
        facultyName.setText(faculty);

        sDate= GlobalVariable.Date;
        subDate = findViewById(R.id.subjectDate_tv);
        subDate.setText("Subject: "+GlobalVariable.SubjectName + " | Date:"+ GlobalVariable.Date);

        listView = findViewById(R.id.listview);

        String subject = GlobalVariable.SubjectName;

        DbHelper db = new DbHelper(getApplicationContext());
        studentTableArrayList = db.getAllStudent();

        for(StudentTable studentTable : studentTableArrayList)
        {
            String studentSubject = studentTable.getStudent_subject();
            String users = studentTable.getStudent_name();
            if(studentSubject.equals(subject)){

                String studentName = studentTable.getStudent_name();
                studentList.add(users);

            }

        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentList);
        listView.setAdapter( listAdapter );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {

                String student = studentList.get(position);
                String statusP = "P";
                String statusA = "A";
                String cSubject=GlobalVariable.SubjectName;

                DbHelper db = new DbHelper(getApplicationContext());
                int no_of_present = db.TotalAttendance(student,statusP,cSubject);
                int no_of_absent = db.TotalAttendance(student,statusA,cSubject);

                int total_attendance=no_of_present+no_of_absent;
                double percent = ((double)no_of_present/total_attendance)*100;
                String percent1=(format("%.2f",percent));


                Intent i = new Intent(getApplicationContext(),StatisticsActivity.class);
                i.putExtra("sNameForStatistics",student);
                i.putExtra("presentCount",no_of_present);
                i.putExtra("absentCount",no_of_absent);
                i.putExtra("Percentage",percent1);
                startActivity(i);

            }
        });
    }
}