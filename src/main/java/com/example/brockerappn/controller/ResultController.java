package com.example.brockerappn.controller;

import com.example.brockerappn.entity.Result;
import com.example.brockerappn.entity.VectorData;
import com.example.brockerappn.service.ResultService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/broker")
@Slf4j
public class ResultController {

    @Autowired
    public ResultService resultService;

    @PostMapping("/data")
    public ResponseEntity<?> sendData(@RequestBody Optional<VectorData> vectorData) {

        if (vectorData.isPresent()) {

            resultService.sendData(vectorData.get().getVectors());

            return ResponseEntity.ok().build();

        } else {

            return ResponseEntity.badRequest().body("No vector data provided");

        }

    }

    @GetMapping("/result")
    public List<Result> getResult() {
        return resultService.getAllResult();
    }

}
