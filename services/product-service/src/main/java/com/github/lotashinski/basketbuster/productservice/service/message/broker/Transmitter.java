package com.github.lotashinski.basketbuster.productservice.service.message.broker;

public interface Transmitter<T> {
    void send(T message);
}
