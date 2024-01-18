package io.mohkeita.orderservice.entity;

import io.mohkeita.orderservice.dto.FoodItemsDto;
import io.mohkeita.orderservice.dto.Restaurant;
import io.mohkeita.orderservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemsDto> foodItemsList;
    private Restaurant restaurant;
    private UserDto userDTO;
}
