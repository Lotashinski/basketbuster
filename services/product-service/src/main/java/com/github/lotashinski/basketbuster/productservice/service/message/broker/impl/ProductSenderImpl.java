package com.github.lotashinski.basketbuster.productservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.ProductEventsSender;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.Transmitter;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.Event;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSenderImpl implements ProductEventsSender {

    private final Transmitter<ProductDto> transmitter;


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
        send(product, Event.DELETE);
    }


    private void send(Product product, Event event) {
        ProductDto message = createMessage(product, event);
        transmitter.send(message);
    }

    private ProductDto createMessage(Product product, Event eventType) {
        ProductDto dto = createDto(product);
        dto.setEvent(eventType);

        return dto;
    }

    private ProductDto createDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .build();
    }

}
