package com.rosydawn.model;

import java.util.Date;

/**
 * Created by Vincent Huang on 2017/8/15.
 */
public class PersonModel {
    private int id;
    private String name;
    private String avatar;
    private int grade;
    private String signature;
    private int rank;
    private Date insertTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", grade=" + grade +
                ", signature='" + signature + '\'' +
                ", rank=" + rank +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public PersonModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

