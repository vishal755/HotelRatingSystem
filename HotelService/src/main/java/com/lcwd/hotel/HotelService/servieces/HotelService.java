package com.lcwd.hotel.HotelService.servieces;

import com.lcwd.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel getHotelById(String hotelId);

    public List<Hotel> getAllHotel();

    public Hotel creatHotel(Hotel hotel);
}
