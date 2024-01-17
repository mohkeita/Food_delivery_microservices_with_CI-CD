package io.mohkeita.foodcatalogueservice.controller;

import io.mohkeita.foodcatalogueservice.dto.FoodCataloguePage;
import io.mohkeita.foodcatalogueservice.dto.FoodItemDto;
import io.mohkeita.foodcatalogueservice.service.FoodCatalogueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogues")
@Log4j2
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;

    @PostMapping
    public ResponseEntity<FoodItemDto> createFoodItem(@RequestBody FoodItemDto foodItemDTO){
        FoodItemDto foodItemSaved = foodCatalogueService.createFoodItem(foodItemDTO);

        log.info("FoundItem Id: {}", foodItemSaved.getId());
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getRestauDetailsWithFoodMenu(@PathVariable Integer restaurantId){
        FoodCataloguePage foodCataloguePage = foodCatalogueService.getFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);


    }
}
