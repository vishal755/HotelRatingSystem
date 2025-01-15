package com.lcwd.ratings.services;

import com.lcwd.ratings.entities.Rating;

import java.util.List;

public interface RatingService{

    public Rating createRating(Rating rating);

    public List<Rating> findAllRatings();

    public List<Rating> findRatingsByUserId(String userId);

    public List<Rating> findRatingsByHotelId(String hotelId);

}
