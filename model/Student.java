/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thaond
 */
public class Student {

    private String id;
    private String studentname;
    private String studentgender;
    private String batchid;

    public Student() {
    }

    public Student(String id, String studentname, String studentgender, String batchid) {
        this.id = id;
        this.studentname = studentname;
        this.studentgender = studentgender;
        this.batchid = batchid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentgender() {
        return studentgender;
    }

    public void setStudentgender(String studentgender) {
        this.studentgender = studentgender;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

}
