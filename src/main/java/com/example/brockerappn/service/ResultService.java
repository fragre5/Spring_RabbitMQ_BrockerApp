package com.example.brockerappn.service;

import com.example.brockerappn.entity.DataMessage;
import com.example.brockerappn.entity.Result;
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

    public void sendData(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            String title = reader.readLine();
            while ((line = reader.readLine()) != null) {
                DataMessage dataMessage = DataMessage.builder()
                        .datetime(LocalDateTime.now().toString())
                        .title(title)
                        .text(line)
                        .build();

                sendMessage(dataMessage);

                log.info("Сообщение отправлено!");

                Thread.sleep(3000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void sendMessage(DataMessage message) {
        rabbitTemplate.convertAndSend("testExchange", "testRoutingKey", message);
    }

}
