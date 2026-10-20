package com.ga.concurrency.controller;

import com.ga.concurrency.model.Employee;
import com.ga.concurrency.service.EmployeeService;
import com.ga.concurrency.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {
    private final EmployeeService employeeService;
    private final FileService fileService;

    /**
     * Uploads an employee CSV, calculates the new salaries concurrently,and writes the updated data to a new CSV file.
     *
     * @param file the employees CSV file
     * @return 200 with the path of the generated file, or 400 if the file is empty
     * @throws IOException the output file cannot be written
     * @throws RuntimeException if the input file cannot be read
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "upload single file")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file) throws IOException {
            if(file.isEmpty()){
                return ResponseEntity.badRequest().body("file is empty.");
            }
            List<Employee> employees = employeeService.processEmployee(file);
            Path filePath = fileService.writeEmployees(employees);
            return ResponseEntity.ok().body("This is the file path for the updated data: " + filePath);
    }
}
