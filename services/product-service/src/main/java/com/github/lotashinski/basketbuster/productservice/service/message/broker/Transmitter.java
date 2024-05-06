package com.github.lotashinski.basketbuster.productservice.service.message.broker;

public interface Transmitter<KEY, T> {
    void send(KEY key,  T message);
}
