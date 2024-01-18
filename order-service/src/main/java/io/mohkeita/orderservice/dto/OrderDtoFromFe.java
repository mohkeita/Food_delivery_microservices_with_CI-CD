package io.mohkeita.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoFromFe {

    private List<FoodItemsDto> foodItemsList;
    private Integer userId;
    private Restaurant restaurant;
}
