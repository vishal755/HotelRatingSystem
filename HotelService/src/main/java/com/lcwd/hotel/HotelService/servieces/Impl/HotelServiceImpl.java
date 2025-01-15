package com.lcwd.hotel.HotelService.servieces.Impl;

import com.lcwd.hotel.HotelService.entities.Hotel;
import com.lcwd.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.HotelService.repositories.HotelRepository;
import com.lcwd.hotel.HotelService.servieces.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel getHotelById(String hotelId) {
        return repository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel not found in server with hotel id: "+hotelId));
    }

    @Override
    public List<Hotel> getAllHotel() {
        return repository.findAll();
    }

    @Override
    public Hotel creatHotel(Hotel hotel) {

        String randomId = UUID.randomUUID().toString();
        hotel.setHotelId(randomId);
        return repository.save(hotel);
    }
}
