package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewClass extends Activity {

    Button submit;
    EditText facultyName;
    EditText subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_new_class);

        facultyName = findViewById(R.id.facultyname_edt);
        subjectName = findViewById(R.id.fSubjectName_edt);
        submit = findViewById(R.id.addbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facultyname = facultyName.getText().toString();
                String subject_name = subjectName.getText().toString();
                if (TextUtils.isEmpty(facultyname)) {
                    facultyName.setError("Please Enter Faculty Name");
                    Toast.makeText(AddNewClass.this, "Please Enter Faculty Name", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(subject_name)) {
                    subjectName.setError("Please Enter Subject Name");
                    Toast.makeText(AddNewClass.this, "Please Enter Subject Name", Toast.LENGTH_SHORT).show();
                } else {

                    ClassTable newcourse = new ClassTable(facultyName.getText().toString(), subjectName.getText().toString());
                    DbHelper dbhelper = new DbHelper(AddNewClass.this);

                    int count = dbhelper.countFacultySubject(subject_name);
                    if (count == 0) {
                        int status = dbhelper.addClass(facultyname, subject_name);
                        if (status == -1) {
                            Toast.makeText(AddNewClass.this, "Insert Error. Try Again", Toast.LENGTH_SHORT).show();

                        } else if (status == 0) {
                            Toast.makeText(AddNewClass.this, " Course Name already present", Toast.LENGTH_SHORT).show();
                        } else if (status == 1) {
                            Toast.makeText(AddNewClass.this, " Course added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        Toast.makeText(AddNewClass.this, "Faculty already Exist with this Subject \nPlease Select a Different Subject Name", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}