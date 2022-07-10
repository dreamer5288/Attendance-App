package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class ViewAttendance extends AppCompatActivity {


    List<StatusTable> statusTableArrayList = new ArrayList<>();
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    TextView date;

    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_attendance);

        listView=(ListView)findViewById(R.id.listview);
        date = findViewById(R.id.date_tv);
        date.setText(GlobalVariable.Date);
        String subject = GlobalVariable.SubjectName;
        String dateToday = GlobalVariable.Date;

        DbHelper db = new DbHelper(getApplicationContext());
        final List<String> presentStudentTableList = new ArrayList<>();
        final List<String> pStudentTableList = new ArrayList<>();
        statusTableArrayList = db.getAllStatusData();

        for(StatusTable statusTable : statusTableArrayList)
        {
            String statusDate =statusTable.getStatus_date();
            String studentStatus =statusTable.getStatus_type();
            String statusSubject =statusTable.getStudent_subject();

            if(statusDate.equals(GlobalVariable.Date)){
                if(studentStatus.equals("P")){
                    if(statusSubject.equals(GlobalVariable.SubjectName)) {
                        String student = statusTable.getStudent_name();
                        pStudentTableList.add(student);

                        String users = "Student Name: " + statusTable.getStudent_name() + "\nStatus: " + statusTable.getStatus_type();
                        presentStudentTableList.add(users);
                    }

                }
            }
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, presentStudentTableList);
        listView.setAdapter( listAdapter );


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String student = pStudentTableList.get(position);
                String statusP = "P";
                String statusA = "A";


                DbHelper db = new DbHelper(getApplicationContext());
               // int no_of_present = db.TotalAttendance(student,statusP);
           //     int no_of_absent = db.TotalAttendance(student,statusA);

                //Toast.makeText(ViewAttendance.this, "Total Present: "+no_of_present+" And Total Absent: "+no_of_absent, Toast.LENGTH_SHORT).show();


               // Toast.makeText(ViewAttendance.this, "Student Name: "+student, Toast.LENGTH_SHORT).show();
            }
        });

    }
}