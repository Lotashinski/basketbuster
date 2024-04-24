package com.github.lotashinski.basketbuster.productservice.service.message.broker;

import com.github.lotashinski.basketbuster.productservice.service.message.broker.dto.Message;

public interface Transmitter<T> {
    void send(Message<T> message);
}
