package com.rosydawn.mybatis.domain;

import java.util.Date;
import java.util.List;

/**
 * 教师类
 *
 * @author Vincent
 * Created on 2018/06/25 10:25 PM
 **/
public class Teacher {
    private long teacherId;
    private String teacherName;
    private int age;
    /**
     * male or female
     */
    private String gender;
    private String course;
    private List<Student> studentList;
    private Date insertTime;
    private Date updateTime;


}
