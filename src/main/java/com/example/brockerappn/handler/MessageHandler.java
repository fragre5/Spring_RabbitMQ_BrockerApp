package com.example.brockerappn.handler;

import com.example.brockerappn.entity.VectorData;
import com.example.brockerappn.repository.ResultRepository;
import com.example.brockerappn.service.VectorCalculationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
public class MessageHandler {

    private final ResultRepository resultRepository;

    private final VectorCalculationsService vectorCalculationsService;

    @Autowired
    public MessageHandler(ResultRepository resultRepository, VectorCalculationsService vectorCalculationsService) {
        this.resultRepository = resultRepository;
        this.vectorCalculationsService = vectorCalculationsService;
    }

    @RabbitListener(queues = "queue1")
    public void handleMessage(VectorData data) {

        vectorCalculations(data);

        log.info("Данные из очереди считаны и сохранены в БД");
    }

    private void vectorCalculations(VectorData data) {

    }
}
