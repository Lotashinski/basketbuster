package com.github.lotashinski.basketbuster.storeservice.service.converter;

@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from, TO to);
}
