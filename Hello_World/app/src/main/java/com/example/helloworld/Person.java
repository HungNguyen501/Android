package com.example.helloworld;

public abstract class Person {
    protected String name;
    protected int age;

    public Person() {
        name = "who_am_i";
        age = 0;
    }

    public  Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void getDetail();
}
