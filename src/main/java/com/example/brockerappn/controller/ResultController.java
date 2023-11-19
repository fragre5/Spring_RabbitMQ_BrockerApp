package com.example.brockerappn.controller;

import com.example.brockerappn.entity.Result;
import com.example.brockerappn.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/broker")
@Slf4j
public class ResultController {

    @Autowired
    public ResultService resultService;

    @PostMapping("/data")
    public ResponseEntity<?> sendData(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            log.error("Ошибка: файл не отправлен или пуст");
            return ResponseEntity.badRequest().body("Файл не отправлен или пуст");
        }

        resultService.sendData(file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public List<Result> getResult() {
        return resultService.getAllResult();
    }

}
