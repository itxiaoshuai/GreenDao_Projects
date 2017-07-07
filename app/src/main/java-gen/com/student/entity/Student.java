package com.student.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "STUDENT".
 */
@Entity
public class Student {

    @Id
    private Long id;
    private String name;
    private String address;
    private Integer age;

    @Generated
    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    @Generated
    public Student(Long id, String name, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", age=" + age + '}';
    }
}
