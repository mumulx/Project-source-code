package com.yanqun.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Person {
    private int id ;
    private String name ;

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
}
