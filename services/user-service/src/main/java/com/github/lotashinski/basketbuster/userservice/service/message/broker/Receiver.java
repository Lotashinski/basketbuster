package com.github.lotashinski.basketbuster.userservice.service.message.broker;

public interface Receiver<T> {

    void receive(T message);

}
