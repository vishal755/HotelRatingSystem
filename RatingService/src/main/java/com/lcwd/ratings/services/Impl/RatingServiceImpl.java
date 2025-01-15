package com.lcwd.ratings.services.Impl;

import com.lcwd.ratings.entities.Rating;
import com.lcwd.ratings.repositories.RatingRepostiory;
import com.lcwd.ratings.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    private Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Autowired
    private RatingRepostiory repostiory;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Rating createRating(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return repostiory.save(rating);
    }

    @Override
    public List<Rating> findAllRatings() {
        return repostiory.findAll();
    }

    @Override
    public List<Rating> findRatingsByUserId(String userId) {
        List<Rating> ratings = repostiory.findByUserId(userId);
        return ratings;
    }

    @Override
    public List<Rating> findRatingsByHotelId(String hotelId) {
        return repostiory.findByHotelId(hotelId);
    }
}
