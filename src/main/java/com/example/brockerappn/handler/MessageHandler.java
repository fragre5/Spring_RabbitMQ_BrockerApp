package com.example.brockerappn.handler;

import com.example.brockerappn.entity.DataMessage;
import com.example.brockerappn.entity.Result;
import com.example.brockerappn.repository.ResultRepository;
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

    @Autowired
    public MessageHandler(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @RabbitListener(queues = "queue1")
    public void handleMessage(DataMessage data) {

        double count = calculateXCount(data.getText());

        Result result = Result.builder()
                .datetime(data.getDatetime())
                .title(data.getTitle())
                .xAvgCountInLine(count)
                .build();

        resultRepository.save(result);
        log.info("Данные из очереди считаны и сохранены в БД");
    }

    private int calculateXCount(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.toLowerCase().charAt(i) == 'х' || text.toLowerCase().charAt(i) == 'x') {
                count++;
            }
        }
        return count;
    }
}
