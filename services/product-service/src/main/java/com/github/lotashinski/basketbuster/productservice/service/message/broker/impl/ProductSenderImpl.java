package com.github.lotashinski.basketbuster.productservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.Message;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.ProductEventsSender;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.Event;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSenderImpl implements ProductEventsSender {

    private final KafkaProductSender kafkaProductSender;

    @Override
    public void create(Product product) {
        send(product, Event.CREATE);

    }

    @Override
    public void update(Product product) {
        send(product, Event.UPDATE);
    }

    @Override
    public void delete(Product product) {
        send(product, Event.UPDATE);
    }


    private void send(Product product, Event event) {
        Message<ProductDto> message = createMessage(product, event);
        kafkaProductSender.send(message);
    }

    private Message<ProductDto> createMessage(Product product, Event eventType) {
        return Message.<ProductDto>builder()
                .value(createDto(product))
                .event(eventType)
                .build();
    }

    private ProductDto createDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .build();
    }

}
