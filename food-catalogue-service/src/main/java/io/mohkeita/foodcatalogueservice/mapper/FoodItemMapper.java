package io.mohkeita.foodcatalogueservice.mapper;

import io.mohkeita.foodcatalogueservice.dto.FoodItemDto;
import io.mohkeita.foodcatalogueservice.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDto foodItemDTO);

    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);
}
