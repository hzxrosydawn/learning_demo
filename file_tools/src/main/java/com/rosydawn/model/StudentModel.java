package com.rosydawn.model;

/**
 * 测试Model类
 *
 * @author Vincent
 * Created on 2018/05/15 8:52 PM
 **/
public class StudentModel {
    private String name;
    private String gender;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
