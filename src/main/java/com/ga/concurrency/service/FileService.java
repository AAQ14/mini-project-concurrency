package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService {

    public List<Employee> readEmployees(MultipartFile file){
        List<Employee> employees = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(file.getInputStream());
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.isEmpty())continue;
                try{
                    String[] parts = line.split(",");
                    Employee employee = new Employee(parts[1].trim(), Double.parseDouble(parts[2].trim()), LocalDate.parse(parts[3].trim()), parts[4].trim(), Double.parseDouble(parts[5].trim()));
                    employees.add(employee);
                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }

            }
        }catch (IOException ex){
            throw new RuntimeException("an exception has occurred: " + ex.getMessage());
        }

        return employees;
    }
}
