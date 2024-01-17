package io.mohkeita.restaurantlisting.controller;

import io.mohkeita.restaurantlisting.dto.RestaurantDto;
import io.mohkeita.restaurantlisting.service.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Log4j2
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> fetchAllRestaurants() {
        List<RestaurantDto> allRestaurants = restaurantService.getRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> findRestaurantById(@PathVariable Integer id) {

        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto restaurantAdded = restaurantService.createRestaurant(restaurantDto);
        log.info("Restaurant Id: {}", restaurantAdded.getId());

        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }


}
