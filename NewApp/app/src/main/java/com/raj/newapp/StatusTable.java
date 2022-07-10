package com.raj.newapp;

public class StatusTable {
    int status_id;
    String status_date;
    String status_type;
    String student_subject;
    String student_name;



    public StatusTable(String status_date, String status_type, String student_subject, String student_name) {
        this.status_date = status_date;
        this.status_type = status_type;
        this.student_subject = student_subject;
        this.student_name = student_name;
    }

    public StatusTable() {
    }

    public StatusTable(int status_id, String status_date, String status_type, String student_subject, String student_name) {
        this.status_id = status_id;
        this.status_date = status_date;
        this.status_type = status_type;
        this.student_subject = student_subject;
        this.student_name = student_name;
    }

    public StatusTable(int status_id, String status_date, String status_type, String student_name) {
        this.status_id = status_id;
        this.status_date = status_date;
        this.status_type = status_type;
        this.student_name = student_name;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus_date() {
        return status_date;
    }

    public void setStatus_date(String status_date) {
        this.status_date = status_date;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public String getStudent_subject() {
        return student_subject;
    }

    public void setStudent_subject(String student_subject) {
        this.student_subject = student_subject;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return "StatusTable{" +
                "status_date='" + status_date + '\'' +
                ", status_type='" + status_type + '\'' +
                ", student_subject='" + student_subject + '\'' +
                ", student_name='" + student_name + '\'' +
                '}';
    }
}
