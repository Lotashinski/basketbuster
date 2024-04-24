package com.github.lotashinski.basketbuster.productservice.service.message.broker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Message<T> {

    @JsonIgnore
    private String topic;

    private T value;

    private Event event;

}
