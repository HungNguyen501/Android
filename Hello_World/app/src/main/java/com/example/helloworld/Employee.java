package com.example.helloworld;

public class Employee extends Person{
    protected int salary;

    public Employee() {
        super();
        this.salary = 0;
    }

    public Employee(String name, int age, int salary) {
        super(name, age);
    }

    public void getDetail() {
        System.out.println("Information: " + name + ", " + age);
    }

    public void calculateSalary() {
        System.out.println("Salary " + salary);
    }
}
