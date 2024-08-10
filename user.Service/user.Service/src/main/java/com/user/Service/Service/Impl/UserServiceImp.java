package com.user.Service.Service.Impl;



import com.user.Service.Entities.Hotel;
import com.user.Service.Entities.Rating;
import com.user.Service.Entities.User;
import com.user.Service.Exceptions.ResourceNotFoundException;
import com.user.Service.Service.UserService;
import com.user.Service.fienClien.Service.HotelService;
import com.user.Service.repository.UserRepository;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService; //feign client communication

//    private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        final String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given Id is not found on server !! "+userId));
            //api call to rating service to get rating with userId
       Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//                logger.info("{}",ratingOfUser);

         List<Rating> ratings = Arrays.stream(ratingOfUser).toList();



        List<Rating> ratingList = ratings.stream().map(rating -> {
                    // api call to hotel service to get hotel with passing ratingId
                    //http://localhost:8082/hotels/c21e0db1-7af4-4faf-ac89-4d830571e141

//              ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
//                logger.info("Response status code: {}",forEntity.getStatusCode());

                rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());


               user.setRatings(ratingList);
               return user;
    }
}
