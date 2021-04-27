package com.example.helloworld;

public class Manager extends Employee implements Functionable{
    protected String role;
    protected static int capbac;

    public Manager() {
        super();
        role = "empty";
        capbac = 5;
    }

    public Manager(String name, int age, int salary, String role) {
        super(name, age, salary);
        this.role = role;
        capbac = 5;
    }

    public void calculateSalary() {
        System.out.println("Salary of manager: " + salary);
    }

    public int sumSalary() {
        return (retireYear - age) * salary;
    }

    public void setCapbac(int capbac) {
        this.capbac = capbac;
    }

}
