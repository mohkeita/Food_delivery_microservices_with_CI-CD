package io.mohkeita.orderservice.service;

import io.mohkeita.orderservice.dto.OrderDto;
import io.mohkeita.orderservice.dto.OrderDtoFromFe;
import io.mohkeita.orderservice.dto.UserDto;
import io.mohkeita.orderservice.entity.Order;
import io.mohkeita.orderservice.mapper.OrderMapper;
import io.mohkeita.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private RestTemplate restTemplate;



    public OrderDto saveOrderInDb(OrderDtoFromFe orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
       // UserDto userDto = fetchUserDetailsFromUserId(orderDetails.getUserId());
        UserDto userDto = null;
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDto );
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(orderToBeSaved);
    }

    private UserDto fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/users/" + userId, UserDto.class);
    }
}
