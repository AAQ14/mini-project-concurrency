package com.ga.concurrency.service;

import com.ga.concurrency.exceptions.InvalidFileException;
import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService {

    /**
     * Reads employees from an uploaded CSV file.
     *
     * @param file the uploaded CSV file
     * @return the list of employees read from the file
     * @throws InvalidFileException if the file is empty or is not a .csv file
     * @throws RuntimeException if the file cannot be read
     */
    public List<Employee> readEmployees(MultipartFile file){
        validate(file);
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


    /**
     * hecks that the uploaded file is not empty and has a .csv extension.
     *
     * @param file the uploaded file
     * @throws InvalidFileException if the file is empty or is not a .csv file
     */
    private void validate(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new InvalidFileException("File is empty");
        }
        String name = file.getOriginalFilename();
        if(name == null || !name.toLowerCase().endsWith(".csv")){
            throw new InvalidFileException("Only .csv files are allowed");
        }
    }


    /**
     * Writes the employees with their old and new salaries to a new CSV file.
     *
     * @param employees the processed employees
     * @return the path of the generated file
     * @throws IOException if the output file cannot be created
     * @throws RuntimeException if writing to the file fails
     */
    public Path writeEmployees(List<Employee> employees) throws IOException {
        String fileName = "updated_employees_" + System.currentTimeMillis() + ".csv";
        FileWriter fw = new FileWriter(fileName);
        try(PrintWriter writer = new PrintWriter(fw)){
            writer.println("name,role,joinedDate,percentCompleted,oldSalary,newSalary");
            for(Employee e: employees){
                writer.println(e.getName() + "," + e.getRole() + "," + e.getJoinedDate()+ "," + e.getPercentCompleted() + "," + e.getSalary() + "," + e.getNewSalary());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not write the file " + e);
        }
        return Path.of(fileName);
    }
}
