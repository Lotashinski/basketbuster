package com.github.lotashinski.basketbuster.orderservice.service.converter;

@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from, TO to);
}
