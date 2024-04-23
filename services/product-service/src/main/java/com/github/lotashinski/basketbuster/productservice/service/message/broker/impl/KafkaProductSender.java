package com.github.lotashinski.basketbuster.productservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.Message;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.Sender;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProductSender implements Sender<ProductDto> {

    private final KafkaTemplate<String, ProductDto> kafkaTemplate;

    @Override
    public void send(Message<ProductDto> message) {
        log.info("Try to send message: {}", message);
        kafkaTemplate.send(message.getTopic(), message.getValue());
    }
}
