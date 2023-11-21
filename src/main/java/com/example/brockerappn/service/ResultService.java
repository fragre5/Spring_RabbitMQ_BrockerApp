package com.example.brockerappn.service;

import com.example.brockerappn.entity.VectorData;
import com.example.brockerappn.entity.Result;
import com.example.brockerappn.entity.VectorPair;
import com.example.brockerappn.repository.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ResultService {

    private final RabbitTemplate rabbitTemplate;
    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(RabbitTemplate rabbitTemplate, ResultRepository resultRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.resultRepository = resultRepository;
    }

    public void sendData(List<VectorPair> vectors) {

        //sendMessage(vectors)

    }

    public List<Result> getAllResult() {
        List<Result> resultList = resultRepository.findAll();
        double total = resultList.stream()
                .mapToDouble(Result::getXAvgCountInLine)
                .sum();

        double avgCount = total / resultList.size();
        resultList.forEach(result -> result.setXAvgCountInLine(avgCount));
        return resultList;
    }

    private void sendMessage(VectorData dataMessage) {
        rabbitTemplate.convertAndSend("testExchange", "testRoutingKey", dataMessage);
    }

}
