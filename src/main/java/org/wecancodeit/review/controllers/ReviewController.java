package org.wecancodeit.review.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.review.models.ReviewModel;
import org.wecancodeit.review.services.ReviewService;


@RestController
@RequestMapping({"/api/review","/api/Review","/API/Review"})
public class ReviewController {
    private ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping
    public ResponseEntity<?> getReviews(){
        return ResponseEntity.ok(reviewService.getReviews());
    }
}
