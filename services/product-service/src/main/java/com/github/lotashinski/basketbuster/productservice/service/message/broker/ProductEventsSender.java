package com.github.lotashinski.basketbuster.productservice.service.message.broker;

import com.github.lotashinski.basketbuster.productservice.entity.Product;

public interface ProductEventsSender {

    void create(Product product);

    void update(Product product);

    void delete(Product product);

}
