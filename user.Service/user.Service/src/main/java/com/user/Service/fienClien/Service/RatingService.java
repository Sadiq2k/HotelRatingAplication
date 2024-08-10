package com.user.Service.fienClien.Service;

import com.user.Service.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("ratings/users/{userId}")
    Rating getRating(@PathVariable String ratingId);

    @PostMapping("/ratings")
    Rating createRating(@RequestBody Rating rating);



}
