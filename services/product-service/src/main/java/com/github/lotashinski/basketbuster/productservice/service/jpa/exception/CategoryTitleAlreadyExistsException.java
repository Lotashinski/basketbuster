package com.github.lotashinski.basketbuster.productservice.service.jpa.exception;

public class CategoryTitleAlreadyExistsException extends RuntimeException {

    public CategoryTitleAlreadyExistsException(String message) {
        super(message);
    }

}
