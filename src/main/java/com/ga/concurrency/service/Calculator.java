package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class Calculator {
    public double calculateNewSalary(Employee employee){
        double increasedSalary =0;
        double salary = employee.getSalary();
        if(employee.getPercentCompleted() < 0.6){
            return salary;
        }
        if(employee.getPercentCompleted()>=0.6){
            long years = employee.getJoinedDate().until(LocalDate.now(), ChronoUnit.YEARS );
            if(employee.getRole().equals("Employee")){
                if(years>=1)increasedSalary =  (salary + ((0.02*years)*salary) + (salary*0.01));
                else increasedSalary =  (salary + (salary*0.01));
            }else if (employee.getRole().equals("Manager")){
                if(years>=1)increasedSalary =  (salary + ((0.02*years)*salary) + (salary*0.02));
                else increasedSalary =  (salary + (salary*0.02));
            }else if (employee.getRole().equals("Director")){
                if(years>=1)increasedSalary =  (salary + ((0.02*years)*salary) + (salary*0.05));
                else increasedSalary =  (salary + (salary*0.05));
            }
        }
        return increasedSalary;
    }
}
