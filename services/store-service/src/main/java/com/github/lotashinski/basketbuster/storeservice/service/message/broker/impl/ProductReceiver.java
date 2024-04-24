package com.github.lotashinski.basketbuster.storeservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.storeservice.service.message.broker.ProductListener;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.Receiver;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto.Message;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductReceiver implements Receiver<ProductDto> {

    private final ProductListener productListener;

    @KafkaListener(topics = {"products"}, containerFactory = "productKafkaListenerContainerFactory")
    @Override
    public void receive(Message<ProductDto> message) {
        log.info("received product message: {}", message);

        switch (message.getEvent()) {
            case CREATE:
                productListener.onCreated(message.getValue());
                break;
            case UPDATE:
                productListener.onUpdated(message.getValue());
                break;
            case DELETE:
                productListener.onDeleted(message.getValue());
                break;
        }
    }
}
