package com.github.lotashinski.basketbuster.storeservice.service.message.broker;

public interface Receiver<T> {

    void receive(T message);

}
