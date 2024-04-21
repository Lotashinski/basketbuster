package com.github.lotashinski.basketbuster.productservice.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryGetDto {

    private Long id;

    private String title;

    private String owner;

    private Long childrenCount;

}
