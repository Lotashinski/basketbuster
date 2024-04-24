package com.github.lotashinski.basketbuster.orderservice.service.message.broker;

public interface Receiver<T> {

    void receive(T message);

}
