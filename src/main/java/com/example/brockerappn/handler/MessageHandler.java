package com.example.brockerappn.handler;

import com.example.brockerappn.entity.VectorPair;
import com.example.brockerappn.repository.ResultRepository;
import com.example.brockerappn.service.VectorCalculationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void handleMessage(List<VectorPair> data) {

        vectorCalculations(data);

    }

    private void vectorCalculations(List<VectorPair> data) {

    }
}
