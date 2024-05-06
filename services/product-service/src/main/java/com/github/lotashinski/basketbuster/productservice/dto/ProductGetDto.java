package com.github.lotashinski.basketbuster.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class ProductGetDto {

    private Long id;

    private String title;

    private String description;

}
