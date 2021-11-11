package com.ioc.entity;

import java.io.Serializable;

/**
 * @author : avloger
 * @date : 2021/11/11 15:35
 */
public class User implements Serializable {
    private String username;
    private String passwd;
    private String id;
    private int age;

    public User(String username, String passwd, String id, int age) {
        this.username = username;
        this.passwd = passwd;
        this.id = id;
        this.age = age;
    }
    public User() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}
