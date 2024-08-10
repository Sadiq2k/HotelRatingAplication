package com.hotel.HotelService.Service.Impl;

import com.hotel.HotelService.Exception.ResourceNotFoundException;
import com.hotel.HotelService.Service.HotelService;
import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        final String hotelId = UUID.randomUUID().toString();

        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel gethotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found"));
    }
}
