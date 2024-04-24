package com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Message<T> {

    private String topic;

    private T value;

    private Event event;

}
