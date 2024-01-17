package io.mohkeita.foodcatalogueservice.service;

import io.mohkeita.foodcatalogueservice.dto.FoodCataloguePage;
import io.mohkeita.foodcatalogueservice.dto.FoodItemDto;
import io.mohkeita.foodcatalogueservice.dto.Restaurant;
import io.mohkeita.foodcatalogueservice.entity.FoodItem;
import io.mohkeita.foodcatalogueservice.mapper.FoodItemMapper;
import io.mohkeita.foodcatalogueservice.repo.FoodItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class FoodCatalogueService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDto createFoodItem(FoodItemDto foodItemDto) {
        log.info("Creating FoodItem Request: {}", foodItemDto);
        FoodItem foodItemSavedInDB = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDto));

        log.info("Created FoodItem with Status CREATED");
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemSavedInDB);
    }

    public FoodCataloguePage getFoodCataloguePageDetails(Integer restaurantId) {
        log.info("Get Food Catalogue details Id : {}", restaurantId);
        List<FoodItem> foodItemList =  getFoodItemList(restaurantId);
        Restaurant restaurant = findRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant findRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurants/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> getFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
