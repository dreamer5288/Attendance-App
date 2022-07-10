package com.raj.newapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    String sqlstatment;
    String sqlstatment1;
    String sqlstatment2;

    public DbHelper(@Nullable Context context) {
        super(context, "attendance.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        sqlstatment = "create table if not exists class(id integer primary key autoincrement, facultyname varchar(40) not null,facultysubject varchar(30))";
        db.execSQL(sqlstatment);


        sqlstatment1 = "create table if not exists student(id integer primary key autoincrement, student_name varchar(40) not null, student_subject varchar(30) not null,student_gender varchar(30),father_name varchar(40) not null,student_contact varchar(15))";
        db.execSQL(sqlstatment1);

        //sqlstatment2 = "create table if not exists status(status_id integer primary key autoincrement, status_date varchar(30), status_type varchar(10),subject varchar(30) not null, student_name varchar(40) not null, foreign key (student_name) references student (student_name) )";

        sqlstatment2 = "create table if not exists status(status_id integer primary key autoincrement, status_date varchar(30), status_type varchar(10),student_subject varchar(30) not null, student_name varchar(40) not null,constraint fk_student foreign key (student_name) references student (student_name),constraint fk_subject foreign key (student_subject) references student (student_subject) )";
        db.execSQL(sqlstatment2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int addClass(String fName, String sName) {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count;

        String sqlstatment4 = "select count(*) from class where facultyname = \"" + fName + "\"";
        cu = db.rawQuery(sqlstatment4, null);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();

            if (count >= 1) {
                cu.close();
                db.close();
                return 0; // course name taken
            }
        }
        cu.close();
        db.close();
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("facultyname", fName);
        cv.put("facultysubject", sName);

        long status = db.insert("class", null, cv);

        if (status > 0) {

            db.close();
            return 1; //insert successfull
        } else {

            db.close();
            return -1; //insert error
        }
    }

    public int addStudent(String student,String subject,String gender,String fatherName,String contact) {
        SQLiteDatabase db;
        // first check if the student name already exists
        db = this.getReadableDatabase();
        Cursor cu;
        int count;
        String[] args = new String[]{student, fatherName};
        cu = db.rawQuery("select count(*) from student where student_name=? AND father_name=?", args);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();

            if (count >= 1) {
                cu.close();
                db.close();
            }
        }
        cu.close();
        db.close();
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("student_name", student);
        cv.put("student_subject", subject);
        cv.put("student_gender", gender);
        cv.put("father_name", fatherName);
        cv.put("student_contact", contact);

       long status = db.insert("student", null, cv);
        if (status > 0) {
            db.close();
            return 1; //insert successfull
        } else {
            db.close();
            return -1; //insert error
        }
    }

    public int countStudentWithFather(String student,String fatherName) {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count=0;
        String[] args = new String[]{student, fatherName};
        cu = db.rawQuery("select count(*) from student where student_name=? AND father_name=?", args);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();
        }
        return  count;
    }

    public int countStudent(String student,String subject) {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count=0;
        String[] args = new String[]{student,subject};
        cu = db.rawQuery("select count(*) from student where student_name=? AND student_subject=?", args);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();
        }
        return  count;
    }

    public int countFacultySubject(String subject) {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count=0;
        String[] args = new String[]{subject};
        cu = db.rawQuery("select count(*) from class where facultysubject=?", args);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();
        }
        return  count;
    }

    public List<ClassTable> getAllClass(){

        List<ClassTable> newList = new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        String sqlstatement6 = "select * from class";
        Cursor cursor;
        cursor = db.rawQuery(sqlstatement6,null);
        if(cursor.moveToFirst()){
            do{
                String facultyname = cursor.getString(1);
                String facultysubject = cursor.getString(2);

                ClassTable obj = new ClassTable(facultyname,facultysubject);
                newList.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return newList;
    }

    public List<StudentTable> getAllStudent(){

        List<StudentTable> newList1 = new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        String sqlstatement7 = "select * from student";
        Cursor cursor;
        cursor = db.rawQuery(sqlstatement7,null);
        if(cursor.moveToFirst()){
            do{
                String student_name = cursor.getString(1);
                String student_subject = cursor.getString(2);
                String student_gender = cursor.getString(3);
                String father_name = cursor.getString(4);
                String contact = cursor.getString(5);

                StudentTable obj = new StudentTable(student_name,student_subject,student_gender,father_name,contact);
                newList1.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return newList1;
    }

    public boolean deleteTeacher(String teacherName) {


        SQLiteDatabase db = this.getWritableDatabase();


        return db.delete("class","facultyname" + "=?",new String[]{teacherName})>0;
    }

    public boolean deleteStudentBySubject(String subName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("status","student_subject" + "=?",new String[]{subName});
        return db.delete("student","student_subject" + "=?",new String[]{subName})>0;

    }

    public boolean deleteStudentByNameAndSubject(String studentName,String sub) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("status","student_subject=? AND student_name=?",new String[]{sub,studentName});

        return db.delete("student","student_subject=? AND student_name=?",new String[]{sub,studentName})>0;

    }

    public int addStatus(String sName,String date,String aStatus,String subject){

        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count;

        String[] args = new String[]{date,sName,subject};
        cu = db.rawQuery("select count(*) from status where status_date=? AND student_name=? AND student_subject=?", args);

        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();

            if (count >= 1) {
                cu.close();
                db.close();
                return 0; // course name taken
            }
        }
        cu.close();
        db.close();
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status_date", date);
        cv.put("status_type", aStatus);
        cv.put("student_subject", subject);
        cv.put("student_name", sName);

        long status = db.insert("status", null, cv);
        if (status > 0) {
            db.close();
            return 1; //insert successfull
        } else {
            db.close();
            return -1; //insert error
        }
    }

    long updateStatus(String sName,String date,String aStatus,String subject){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status_type",aStatus);
       // String whereClause = "status_date"+ "='" +date+ "' AND "+"student_name"+ "='" +sName;
        String[] args = new String[]{date, sName,subject};
        return database.update("status",values, "status_date=? AND student_name=? AND student_subject=?",args);

    }

    String getStatus(String sName,String date){
        String st ="null";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = "status_date" + "='" +date+ "'AND " +"student_name"+"="+sName;

        Cursor cursor = database.query("status",null,whereClause,null,null,null,null);
        if(cursor.moveToFirst())
            st = cursor.getString(cursor.getColumnIndex("status_type"));
        return st;
    }

    String getFatherName(String sName,String subName){
        String st ="null";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = "student_name" + "='" +sName+ "'AND " +"student_subject"+"="+subName;

        Cursor cursor = database.query("student",null,whereClause,null,null,null,null);
        if(cursor.moveToFirst())
            st = cursor.getString(cursor.getColumnIndex("father_name"));
        return st;
    }

    public List<StatusTable> getAllStatusData(){

        List<StatusTable> newList3 = new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        String sqlstatement10 = "select * from status";
        Cursor cursor;
        cursor = db.rawQuery(sqlstatement10,null);
        if(cursor.moveToFirst()){
            do{
                String status_date = cursor.getString(1);
                String status_type = cursor.getString(2);
                String student_subject = cursor.getString(3);
                String student_name = cursor.getString(4);

                StatusTable obj = new StatusTable(status_date,status_type,student_subject,student_name);
                newList3.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return newList3;
    }

    public int TotalAttendance(String studentName,String status,String cSubject){

        //List<StatusTable> newList3 = new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cu;
        int count=0;

        String[] args = new String[]{status, studentName,cSubject};
        cu = db.rawQuery("select count(*) from status where status_type=? AND student_name=? AND student_subject=?", args);

        if (cu.moveToFirst()) {
                count = cu.getInt(0);

        }

        cu.close();
        db.close();
        return count;

    }


}