package com.raj.newapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabT;
    List<ClassTable> classTableArrayList = new ArrayList<>();
    ListView lv;
    Button viewTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        fabT = findViewById(R.id.addTeacherbtn);
        lv = findViewById(R.id.lv_teacher);
        viewTeacher = findViewById(R.id.viewTeacherbtn);


        viewTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db = new DbHelper(getApplicationContext());
                final List<String> classTableList = new ArrayList<>();
                classTableArrayList = db.getAllClass();

                for(ClassTable classTable : classTableArrayList)
                {
                    String users = "Teacher Name: " + classTable.getFacultyname()+"\nSubject:"+classTable.getFacultysubject();
                    classTableList.add(users);
                    //Log.d("users: ", users);
                }

                ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,classTableList);
                lv.setAdapter(courseAdapter);
            }
        });


        fabT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddNewClass.class);
                startActivity(i);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setTitle("Delete This Faculty!");
                alertDialogBuilder.setMessage("Are you sure want to delete?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        DbHelper db = new DbHelper(getApplicationContext());
                        String faculty_Name = classTableArrayList.get(position).getFacultyname();
                        String faculty_Subject = classTableArrayList.get(position).getFacultysubject();
                        db.deleteTeacher(classTableArrayList.get(position).getFacultyname());
                        db.deleteStudentBySubject(faculty_Subject);

                        Toast.makeText(MainActivity.this, " Deleted ", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
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