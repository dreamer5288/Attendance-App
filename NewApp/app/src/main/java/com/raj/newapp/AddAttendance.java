package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddAttendance extends AppCompatActivity {

    private ListView listView ;
    List<StudentTable> studentTableArrayList;


    String faculty;
    int count[] =new int[100000];
    String status = "P";
    final ArrayList<String> studentList = new ArrayList<String>();
    TextView facultyName;
    TextView subDate;
    String sDate;
    String currentSubject;
    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_attendance);

        facultyName = findViewById(R.id.facultyName_tv);

        faculty = GlobalVariable.FacultyName;
        currentSubject= GlobalVariable.SubjectName;

        facultyName.setText(faculty);

       sDate= GlobalVariable.Date;

        subDate = findViewById(R.id.subjectDate_tv);
        subDate.setText("Subject: "+GlobalVariable.SubjectName + " | Date:"+ GlobalVariable.Date);

        listView = findViewById(R.id.listview);


        DbHelper db = new DbHelper(getApplicationContext());

        String subject = GlobalVariable.SubjectName;
        String faculty_name=GlobalVariable.FacultyName;
        studentTableArrayList = db.getAllStudent();

        for(StudentTable studentTable : studentTableArrayList)
        {
            String studentSubject = studentTable.getStudent_subject();

            String users = studentTable.getStudent_name();
            if(studentSubject.equals(subject)){


                    String studentName = studentTable.getStudent_name();
                    studentList.add(users);

                    DbHelper database = new DbHelper(this);
                    int status = database.addStatus(studentName,sDate,"A",studentSubject);
                    // int status = dbhelper.addClass(facultyname, subjectname);
                    if (status == -1) {
                        Toast.makeText(AddAttendance.this, "Insert Error. Try Again", Toast.LENGTH_SHORT).show();

                    } else if (status == 0) {
                        Toast.makeText(AddAttendance.this, " Attendance already taken, Please go to Update Section", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status == 1) {

                    }

            }

        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentList);
        listView.setAdapter( listAdapter );


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {

                for (int i = 0; i < listView.getChildCount(); i++) {
                    if(position == i ){

                        int s;
                        s = count[position];
                        s=s%2;
                        if(s==0){
                            listView.getChildAt(i).setBackgroundColor(Color.GREEN);
                            status = "P";
                            count[position] =1;

                            String student = studentList.get(position);

                            DbHelper db = new DbHelper(getApplicationContext());
                            db.updateStatus(student,sDate,status,currentSubject);

                            Toast.makeText(AddAttendance.this, "student " +student +" is : " + status, Toast.LENGTH_SHORT).show();
                        }else{
                            listView.getChildAt(i).setBackgroundColor(Color.RED);
                            status = "A";
                            count[position] = 0;

                            String student = studentList.get(position);

                            DbHelper db = new DbHelper(getApplicationContext());
                            db.updateStatus(student,sDate,status,currentSubject);

                            Toast.makeText(AddAttendance.this, "student " +student +" is : " + status, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}