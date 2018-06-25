package com.rosydawn.mybatis.domain;

/**
 * 学生类
 *
 * @author Vincent
 **/
public class Student {
    private long studentId;
    private String studentName;
    private int age;
    /**
     * male or female
     */
    private String gender;
    /**
     * 年级
     */
    private String grade;


    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
