package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManageStudent extends AppCompatActivity {

    FloatingActionButton fabS;
    ListView lvS;
    Button viewStudent;
    String subject;
    String teacher;
    List<StudentTable> studentTableArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manage_student);

        lvS = findViewById(R.id.lv_student);
        viewStudent = findViewById(R.id.viewStudentbtn);
        fabS = findViewById(R.id.addStudentbtn);

       final List<String> studentTableList = new ArrayList<>();
        List<String> studentNameList = new ArrayList<>();
        List<String> studentSubjectList = new ArrayList<>();


        Intent intent = getIntent();
        subject = intent.getStringExtra("tSubject");
        teacher = intent.getStringExtra("teacher");


        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getApplicationContext());
                 List<String> studentTableList = new ArrayList<>();


                studentTableArrayList = db.getAllStudent();

                for(StudentTable studentTable : studentTableArrayList)
                {
                    String studentSubject =studentTable.getStudent_subject();

                    int studentId =studentTable.getSid();
                    if(studentSubject.equals(subject)){


                            String users = "Student Name: " + studentTable.getStudent_name()+"\nSubject:"+studentTable.getStudent_subject()+"\nGender:"+studentTable.getStudent_gender();
                            studentTableList.add(users);
                            studentNameList.add(studentTable.getStudent_name());
                            studentSubjectList.add(studentTable.getStudent_subject());

                    }
                }
                ArrayAdapter studentAdapter= new ArrayAdapter<String>(ManageStudent.this,android.R.layout.simple_expandable_list_item_1,studentTableList);
                lvS.setAdapter(studentAdapter);
            }
        });

        fabS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddNewStudent.class);
                i.putExtra("sSubject",subject);
                i.putExtra("sTeacher",teacher);
                startActivity(i);
            }
        });

        lvS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ManageStudent.this);

               // alertDialogBuilder.setTitle(getTitle()+" Decision!");
                alertDialogBuilder.setTitle("Delete This Student");
                alertDialogBuilder.setMessage("Are you sure want to delete?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        DbHelper db = new DbHelper(getApplicationContext());
                        String student_Name = studentNameList.get(position);
                        String student_Subject = studentSubjectList.get(position);
                        db.deleteStudentByNameAndSubject(student_Name,student_Subject);


                        Toast.makeText(ManageStudent.this," Deleted ", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),ManageClasses.class);
                        startActivity(i);
                        finish();



                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();


            }
        });

    }
}