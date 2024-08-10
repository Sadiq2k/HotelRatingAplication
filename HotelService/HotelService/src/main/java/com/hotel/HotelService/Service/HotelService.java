package com.hotel.HotelService.Service;

import com.hotel.HotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel gethotel(String id);
}
