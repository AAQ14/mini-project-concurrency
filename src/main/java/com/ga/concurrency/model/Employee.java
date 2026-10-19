package com.ga.concurrency.model;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private String name;
    private double salary;
    private LocalDate joinedDate;
    private String role;
    private double percentCompleted;

    public Employee(String name, long salary, LocalDate joinedDate, String role, double percentCompleted) {
        this.name = name;
        this.salary = salary;
        this.joinedDate = joinedDate;
        this.role = role;
        this.percentCompleted = percentCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(double percentCompleted) {
        this.percentCompleted = percentCompleted;
    }
}
