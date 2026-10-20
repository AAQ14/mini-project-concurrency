package com.ga.concurrency.controller;

import com.ga.concurrency.model.Employee;
import com.ga.concurrency.service.EmployeeService;
import com.ga.concurrency.service.FileService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file) throws IOException {
            if(file.isEmpty()){
                return ResponseEntity.badRequest().body("file is empty.");
            }
            List<Employee> employees = employeeService.processEmployee(file);
            Path filePath = fileService.writeEmployees(employees);
            return ResponseEntity.ok().body("This is the file path for the updated data: " + filePath);
    }
}
