package com.github.lotashinski.basketbuster.orderservice.service.message.broker;

import com.github.lotashinski.basketbuster.orderservice.service.message.broker.dto.ProductDto;

public interface ProductListener {

    void onCreated(ProductDto product);

    void onUpdated(ProductDto product);

    void onDeleted(ProductDto product);

}
