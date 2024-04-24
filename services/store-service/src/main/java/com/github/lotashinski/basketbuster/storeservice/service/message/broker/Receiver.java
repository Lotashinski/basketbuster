package com.github.lotashinski.basketbuster.storeservice.service.message.broker;

import com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto.Message;

public interface Receiver<T> {

    void receive(Message<T> message);

}
