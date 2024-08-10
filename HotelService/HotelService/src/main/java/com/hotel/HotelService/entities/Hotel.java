package com.hotel.HotelService.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;


}
