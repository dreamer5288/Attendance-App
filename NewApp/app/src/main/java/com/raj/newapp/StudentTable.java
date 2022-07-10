package com.raj.newapp;

public class StudentTable {

    int sid;
    String student_name;
    String student_subject;
    String student_gender;
    String father_name;
    String student_contact;


    public StudentTable(String student_name, String student_subject, String student_gender, String father_name, String student_contact) {
        this.student_name = student_name;
        this.student_subject = student_subject;
        this.student_gender = student_gender;
        this.father_name = father_name;
        this.student_contact = student_contact;
    }

    public StudentTable(String student_name, String student_subject, String student_status) {
        this.student_name = student_name;
        this.student_subject = student_subject;
        this.student_gender = student_status;
    }

    public StudentTable(int sid, String student_name, String student_subject, String student_status) {
        this.sid = sid;
        this.student_name = student_name;
        this.student_subject = student_subject;
        this.student_gender = student_status;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    @Override
    public String toString() {
        return "StudentTable{" +
                "student_name='" + student_name + '\'' +
                ", student_subject='" + student_subject + '\'' +
                ", student_gender='" + student_gender + '\'' +
                '}';
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getStudent_contact() {
        return student_contact;
    }

    public void setStudent_contact(String student_contact) {
        this.student_contact = student_contact;
    }

    public StudentTable(int sid, String student, String subject) {
        this.sid = sid;
        this.student_name = student;
        this.student_subject = subject;
    }

    public StudentTable(String student, String subject) {
        this.student_name = student;
        this.student_subject = subject;

    }



    public int getSid() {
        return sid;
    }

    public void setSid(int id) {
        this.sid = id;
    }


    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_subject() {
        return student_subject;
    }

    public StudentTable() {
    }

    public void setStudent_subject(String student_subject) {
        this.student_subject = student_subject;
    }

}
