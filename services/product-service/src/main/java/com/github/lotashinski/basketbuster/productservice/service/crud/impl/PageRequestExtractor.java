package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.service.crud.Paginator;
import org.springframework.data.domain.PageRequest;

final class PageRequestExtractor {

    public static PageRequest extractPageRequest(Paginator paginator) {
        return PageRequest.of(paginator.getPageNumber(), paginator.getPageSize());
    }

}
