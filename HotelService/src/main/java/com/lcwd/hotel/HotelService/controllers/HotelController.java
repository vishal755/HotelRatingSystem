package com.lcwd.hotel.HotelService.controllers;

import com.lcwd.hotel.HotelService.entities.Hotel;
import com.lcwd.hotel.HotelService.servieces.Impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping()
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){

        Hotel newHotel = hotelService.creatHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){

       Hotel hotel1 = hotelService.getHotelById(hotelId);
       return ResponseEntity.status(HttpStatus.OK).body(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelById(){

        List<Hotel> hotels = hotelService.getAllHotel();
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }
}
