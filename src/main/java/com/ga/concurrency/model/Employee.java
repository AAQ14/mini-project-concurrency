package com.ga.concurrency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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
}
