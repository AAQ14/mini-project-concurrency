package com.ga.concurrency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * Represents one employee row from the CSV file.
 * Holds the original salary and the calculated new salary.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private String name;
    private double salary;
    private LocalDate joinedDate;
    private String role;
    private double percentCompleted;
    private double newSalary;

    /**
     * Creates an employee from CSV data. The new salary starts equal to the current salary until the raise is calculated.
     *
     * @param name   the employee name
     * @param salary the current salary
     * @param joinedDate the date the employee joined
     * @param role the employee role
     * @param percentCompleted the percentage of the project completion
     */
    public Employee(String name,  double salary, LocalDate joinedDate, String role, double percentCompleted ){
        this.name = name;
        this.salary = salary;
        this.joinedDate = joinedDate;
        this.role = role;
        this.percentCompleted = percentCompleted;
        this.newSalary = salary;
    }

}
