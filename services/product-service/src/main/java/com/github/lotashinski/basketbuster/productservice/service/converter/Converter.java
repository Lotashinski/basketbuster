package com.github.lotashinski.basketbuster.productservice.service.converter;

@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from, TO to);
}
