package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final FileService fileService;
    private final Calculator calculator;

    public List<Employee> processEmployee(MultipartFile file){

        List<Employee> employees = fileService.readEmployees(file);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for(Employee e: employees){
            executor.execute(()->
                    e.setNewSalary(calculator.calculateNewSalary(e)));
        }

        executor.shutdown();

        try{
            if(!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)){
                throw new RuntimeException("Processing timed out");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return employees;
    }

}
