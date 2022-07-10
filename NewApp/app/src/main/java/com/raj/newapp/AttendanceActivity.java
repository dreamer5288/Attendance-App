package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    List<ClassTable> classTableList;
    List<ClassTable> classTableArrayList = new ArrayList<>();
    ListView lvFaculty;
    Button viewFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_attendance);

        lvFaculty = findViewById(R.id.lv_attendance);
        viewFaculty = findViewById(R.id.viewFaculty_btn);

        viewFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getApplicationContext());
                List<String> classTableList = new ArrayList<>();
                classTableArrayList = db.getAllClass();

                for(ClassTable classTable : classTableArrayList)
                {
                    String users = "Teacher Name: " + classTable.getFacultyname()+"\nSubject:"+classTable.getFacultysubject();
                    classTableList.add(users);
                    Log.d("users: ", users);

                }


                ArrayAdapter courseAdapter= new ArrayAdapter<String>(AttendanceActivity.this,android.R.layout.simple_expandable_list_item_1,classTableList);
                lvFaculty.setAdapter(courseAdapter);

            }
        });

        lvFaculty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int facultyId = classTableArrayList.get(position).getId();
                String teacher = classTableArrayList.get(position).getFacultyname();
                String subject = classTableArrayList.get(position).getFacultysubject();
                Intent i =(new Intent(getApplicationContext(),AttendanceSessionActivity.class));
                i.putExtra("teacher", teacher);
                i.putExtra("subject", subject);
                i.putExtra("teacherId", facultyId);
                startActivity(i);

            }
        });


    }
}