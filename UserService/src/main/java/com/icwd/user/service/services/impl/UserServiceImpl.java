package com.icwd.user.service.services.impl;

import com.icwd.user.service.Exceptions.ResourceNotFoundException;
import com.icwd.user.service.entities.Hotel;
import com.icwd.user.service.entities.Rating;
import com.icwd.user.service.entities.User;
import com.icwd.user.service.externalService.HotelService;
import com.icwd.user.service.repositories.UserRepository;
import com.icwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        //Generate random unique id
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        for(User user : users){
            Rating[] allRatingsByUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

            List<Rating> ratings = Arrays.stream(allRatingsByUser).toList();
            user.setRatings(ratings);

            List<Rating> ratingList = ratings.stream().map(rating ->{
                //getForEntity() --> for Http response related info like status code etc.
                ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

                Hotel hotel = responseEntity.getBody();

                rating.setHotel(hotel);

                return rating;
            }).collect(Collectors.toList());
        }
        return users;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found in server with user id: "+userId));
        //localhost:8083/ratings/users/00557d02-2653-4f94-91da-bcc0c6610af6

        Rating[] allRatingsByUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(allRatingsByUser).toList();
        user.setRatings(ratings);

        logger.info("All the ratings of user: "+ allRatingsByUser);

        List<Rating> ratingList = ratings.stream().map(rating ->{
            //getForEntity() --> for Http response related info like status code etc.
            //ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            //Hotel hotel = responseEntity.getBody();

            //Using feign client to call an hotel service API
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
