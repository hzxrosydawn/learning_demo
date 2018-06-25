package com.rosydawn.mybatis.domain;

import java.util.Date;

/**
 * 成绩类
 *
 * @author Vincent
 **/
public class Score {
    private long scoreId;
    private String courseName;
    private int scoreNum;
    private long studentId;
    /**
     * 考试次数。从1开始递增
     */
    private int examNum;
    private Date insertTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", courseName='" + courseName + '\'' +
                ", scoreNum=" + scoreNum +
                ", studentId=" + studentId +
                ", examNum=" + examNum +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(int scoreNum) {
        this.scoreNum = scoreNum;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getExamNum() {
        return examNum;
    }

    public void setExamNum(int examNum) {
        this.examNum = examNum;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
