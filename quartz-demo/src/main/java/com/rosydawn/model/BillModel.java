package com.rosydawn.model;

import java.util.Date;

/**
 * Created by Vincent Huang on 2017/8/15.
 */
public class BillModel {
    private int  id;
    private float cost;
    private int payerId;
    private int consumerId;
    private String description;
    private String site;
    private int status;
    private Date insertTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "BillModel{" +
                "id=" + id +
                ", cost=" + cost +
                ", payerId=" + payerId +
                ", consumerId=" + consumerId +
                ", description='" + description + '\'' +
                ", site='" + site + '\'' +
                ", status=" + status +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public BillModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
