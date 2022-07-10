package com.raj.newapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    CardView cardViewTeacher;
    CardView cardViewClass;
    CardView cardViewAttendance;
    CardView cardViewAboutInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        cardViewTeacher = findViewById(R.id.card_teacher);
        cardViewClass = findViewById(R.id.card_class);
        cardViewAttendance = findViewById(R.id.card_attendance);
        cardViewAboutInfo = findViewById(R.id.card_aboutInfo);

        cardViewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AttendanceActivity.class);
                startActivity(i);
            }
        });

        cardViewTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
        cardViewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ManageClasses.class);
                startActivity(i);

            }
        });

        cardViewAboutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),AboutInfo.class);
                startActivity(i);

            }
        });

    }
}