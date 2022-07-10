package com.raj.newapp;

public class ClassTable {

    int id;
    String facultyname;
    String facultysubject;


    public ClassTable(int id,String facultyname, String facultysubject ) {
        this.facultyname = facultyname;
        this.facultysubject = facultysubject;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getFacultysubject() {
        return facultysubject;
    }

    public void setFacultysubject(String facultysubject) {
        this.facultysubject = facultysubject;
    }

    public ClassTable() {
    }

    public ClassTable(String facultyname, String facultysubject) {
        this.facultyname = facultyname;
        this.facultysubject = facultysubject;
    }

    @Override
    public String toString() {
        return "ClassTable{" +
                "facultyname='" + facultyname + '\'' +
                ", facultysubject='" + facultysubject + '\'' +
                '}';
    }
}
