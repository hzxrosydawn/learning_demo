package com.rosydawn.model;

import java.util.Date;

/**
 * Created by Vincent Huang on 2017/8/15.
 */
public class AvatarModel {
    private int id;
    private int personId;
    private String avatar;
    // 0表示当前可用，其他数字表示历史头像链接
    private int status;
    private Date insertTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "AvatarModel{" +
                "id=" + id +
                ", personId=" + personId +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public AvatarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
