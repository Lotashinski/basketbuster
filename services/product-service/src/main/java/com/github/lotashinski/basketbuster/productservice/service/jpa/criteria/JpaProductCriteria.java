package com.github.lotashinski.basketbuster.productservice.service.jpa.criteria;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class JpaProductCriteria {

    private String title;

    private List<Category> categories;

}
