package com.github.lotashinski.basketbuster.productservice.dto;

import com.github.lotashinski.basketbuster.productservice.service.crud.Paginator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class CategoryCriteria implements Paginator {

    @Min(0)
    private int pageNumber = 0;

    @Min(1)
    @Max(50)
    private int pageSize = 20;

    @Min(1)
    @Null
    private Long ownerId = null;

}
