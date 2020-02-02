package com.ycit.entity;

import java.util.List;

public class Persion {
    private String name;
    private int age;
    private boolean sex;
    private List<String> hobbies;
    private Other other;
    private String country;
private String favouriteBall;
    public String getCountry() {
        return country;
    }

    public String getFavouriteBall() {
        return favouriteBall;
    }

    public void setFavouriteBall(String favouriteBall) {
        this.favouriteBall = favouriteBall;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
