package io.mohkeita.restaurantlisting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
