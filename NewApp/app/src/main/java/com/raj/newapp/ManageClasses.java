package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManageClasses extends AppCompatActivity {

    FloatingActionButton fabC;
    ListView lvC;
    Button viewClass;
    private  ArrayAdapter studentAdapter;
    final List<String> studentTableList = new ArrayList<>();
    List<ClassTable> classTableArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manage_classes);

        fabC = findViewById(R.id.addClassbtn);
        lvC = findViewById(R.id.lv_class);
        viewClass = findViewById(R.id.viewClassbtn);

        viewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db = new DbHelper(getApplicationContext());
                final List<String> classTableList = new ArrayList<>();
                classTableArrayList = db.getAllClass();

                for(ClassTable classTable : classTableArrayList)
                {
                    String users = "Subject: " + classTable.getFacultysubject() +"\nTeacher Name:"+classTable.getFacultyname();
                    classTableList.add(users);
                }

                ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(ManageClasses.this,android.R.layout.simple_expandable_list_item_1,classTableList);
                lvC.setAdapter(courseAdapter);
            }
        });

        fabC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddNewClass.class);
                startActivity(i);

            }
        });

        lvC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String teacher = classTableArrayList.get(position).getFacultyname();
                String subject = classTableArrayList.get(position).getFacultysubject();

                Intent i = new Intent(getApplicationContext(),ManageStudent.class);
                i.putExtra("teacher",teacher);
                i.putExtra("tSubject",subject);
                startActivity(i);
                finish();

            }
        });


    }
}
