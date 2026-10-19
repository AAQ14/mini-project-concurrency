package com.ga.concurrency.controller;

import com.ga.concurrency.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file){
            if(file.isEmpty()){
                return ResponseEntity.badRequest().body("file is empty.");
            }
            fileService.readEmployees(file);
            return ResponseEntity.ok().body(file.getOriginalFilename() + " is uploaded");
    }
}
