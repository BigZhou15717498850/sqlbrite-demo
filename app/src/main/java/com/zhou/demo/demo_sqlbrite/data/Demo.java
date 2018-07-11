package com.zhou.demo.demo_sqlbrite.data;

import android.support.annotation.NonNull;

/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public class Demo {

    @NonNull
    private String username;
    private String description;
    private String age;
    private String sex;
    private boolean enable;

    public Demo(@NonNull String username, String description, String age, String sex, boolean enable) {
        this.username = username;
        this.description = description;
        this.age = age;
        this.sex = sex;
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", enable=" + enable +
                '}';
    }
}
