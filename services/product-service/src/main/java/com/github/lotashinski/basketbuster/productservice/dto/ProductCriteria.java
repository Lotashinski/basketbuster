package com.github.lotashinski.basketbuster.productservice.dto;

import com.github.lotashinski.basketbuster.productservice.service.crud.Paginator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class ProductCriteria implements Paginator {

    @Min(0)
    private int pageNumber = 0;

    @Min(1)
    @Max(50)
    private int pageSize = 20;

    private String title;

    private Long[] categories;

}
