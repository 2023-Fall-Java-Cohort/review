package org.wecancodeit.review.services;

import org.springframework.stereotype.Service;
import org.wecancodeit.review.models.ReviewModel;
import org.wecancodeit.review.repositories.ReviewRepository;

import jakarta.annotation.Resource;

@Service
public class ReviewService {
    @Resource
    private ReviewRepository reviewRepository;

    
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public Iterable<ReviewModel> getReviews(){
        return reviewRepository.findAll();
    }
}
