package io.mohkeita.restaurantlisting.service;

import io.mohkeita.restaurantlisting.dto.RestaurantDto;
import io.mohkeita.restaurantlisting.entity.Restaurant;
import io.mohkeita.restaurantlisting.mapper.RestaurantMapper;
import io.mohkeita.restaurantlisting.repo.RestaurantRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;

    public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();

        return restaurants.stream().map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto).toList();
    }

    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        log.info("Creating Restaurant Request: {}", restaurantDto);

        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.
                INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto));

        log.info("Created Restaurant with Status CREATED");
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(savedRestaurant);
    }

    public ResponseEntity<RestaurantDto> getRestaurant(Integer id) {
        log.info("Get Restaurant details for Restaurant Id : {}", id);

        Optional<Restaurant> restaurant = restaurantRepo.findById(id);

        if (restaurant.isPresent()) {
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}
