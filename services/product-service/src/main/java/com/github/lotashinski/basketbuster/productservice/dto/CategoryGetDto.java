package com.github.lotashinski.basketbuster.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class CategoryGetDto {

    private Long id;

    private String title;

    private Long ownerId;

    private Long childrenCount;

}
