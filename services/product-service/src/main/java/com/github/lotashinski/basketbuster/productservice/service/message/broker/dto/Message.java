package com.github.lotashinski.basketbuster.productservice.service.message.broker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Message<T> {

    private String topic;

    private T value;

    private Event event;

}
