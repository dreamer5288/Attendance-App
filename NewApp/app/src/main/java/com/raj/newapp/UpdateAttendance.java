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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UpdateAttendance extends AppCompatActivity {

    List<StatusTable> statusTableArrayList = new ArrayList<>();
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    FloatingActionButton fabRefresh;
    TextView date;
    String status="A";
    int count[] =new int[100000];
    String dateToday = GlobalVariable.Date;
    String currentSubject = GlobalVariable.SubjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_attendance);

        listView=(ListView)findViewById(R.id.listView_update);
        fabRefresh = findViewById(R.id.refresh_btn);
        date = findViewById(R.id.update_date_tv);
        date.setText(GlobalVariable.Date);
        String subject = GlobalVariable.SubjectName;


        DbHelper db = new DbHelper(getApplicationContext());
        final List<String> presentStudentTableList = new ArrayList<>();
        final List<String> pStudentTableList = new ArrayList<>();
        statusTableArrayList = db.getAllStatusData();

        for(StatusTable statusTable : statusTableArrayList)
        {
            String statusDate =statusTable.getStatus_date();
            String statusSubject =statusTable.getStudent_subject();
            String studentStatus =statusTable.getStatus_type();

            if(statusDate.equals(GlobalVariable.Date)){
                if(statusSubject.equals(GlobalVariable.SubjectName)) {

                    String users = "Student Name: " + statusTable.getStudent_name() + "\nStatus:" + statusTable.getStatus_type();
                    presentStudentTableList.add(users);
                    pStudentTableList.add(statusTable.getStudent_name());
                }
            }
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, presentStudentTableList);
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

                            String student = pStudentTableList.get(position);

                            DbHelper db = new DbHelper(getApplicationContext());
                            db.updateStatus(student,dateToday,status,currentSubject);

                            Toast.makeText(UpdateAttendance.this, "student " +student +" is : " + status, Toast.LENGTH_SHORT).show();
                        }else{
                            listView.getChildAt(i).setBackgroundColor(Color.RED);
                            status = "A";
                            count[position] = 0;

                            String student = pStudentTableList.get(position);

                            DbHelper db = new DbHelper(getApplicationContext());
                            db.updateStatus(student,dateToday,status,currentSubject);
                            //String newData = student + "\n"+status;
                            String newData =  "Student Name: " + student+"\nStatus:"+status;
                           // pStudentTableList.set(position,newData);

                            Toast.makeText(UpdateAttendance.this, "student " +student +" is : " + status, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ProgressBar p1 = new ProgressBar(getApplicationContext());

                Intent i=new Intent(getBaseContext(),UpdateAttendance.class);
                startActivity(i);
                finish();

               // p1.setProgressDrawable(R.drawable.ic_refresh);
                /****** Create Thread that will sleep for 1 seconds****/

/*
                Thread background = new Thread() {
                    public void run() {
                        try {
                            // Thread will sleep for 1 seconds
                            sleep(1*1000);

                            // After 5 seconds redirect to another intent
                            Intent i=new Intent(getBaseContext(),UpdateAttendance.class);
                            startActivity(i);

                            //Remove activity
                            finish();
                        } catch (Exception e) {
                        }
                    }
                };
                // start thread
                background.start();

 */

            }
        });

    }
}