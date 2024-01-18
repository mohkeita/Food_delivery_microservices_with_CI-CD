package io.mohkeita.orderservice.mapper;

import io.mohkeita.orderservice.dto.OrderDto;
import io.mohkeita.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order mapOrderDtoToOrder(OrderDto orderDto);
    OrderDto mapOrderToOrderDto(Order order);
}
