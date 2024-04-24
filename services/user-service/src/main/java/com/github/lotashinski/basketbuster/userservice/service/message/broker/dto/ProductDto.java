package com.github.lotashinski.basketbuster.userservice.service.message.broker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String title;

    private Event event;
}
