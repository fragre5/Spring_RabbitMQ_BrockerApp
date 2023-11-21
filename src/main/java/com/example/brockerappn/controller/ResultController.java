package com.example.brockerappn.controller;

import com.example.brockerappn.entity.Result;
import com.example.brockerappn.entity.VectorData;
import com.example.brockerappn.entity.VectorPair;
import com.example.brockerappn.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broker")
@Slf4j
public class ResultController {

    @Autowired
    public ResultService resultService;

    @PostMapping("/data")
    public ResponseEntity<?> sendData(@RequestBody VectorData vectorData) {

        List<VectorPair> vectors = vectorData.getVectors();

        resultService.sendData(vectors);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public List<Result> getResult() {
        return resultService.getAllResult();
    }

}
