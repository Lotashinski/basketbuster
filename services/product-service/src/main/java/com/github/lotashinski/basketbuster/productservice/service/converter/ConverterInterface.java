package com.github.lotashinski.basketbuster.productservice.service.converter;

@FunctionalInterface
public interface ConverterInterface<FROM, TO> {
    TO convert(FROM from, TO to);
}
