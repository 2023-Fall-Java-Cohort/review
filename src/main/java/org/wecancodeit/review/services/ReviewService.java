package org.wecancodeit.review.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.wecancodeit.review.NoFoundException;
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

    public ReviewModel getReviewsById(long id) throws NoFoundException {
        Optional<ReviewModel> opt = reviewRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NoFoundException("Review Id " + id + " was not found");

    }

    public Iterable<ReviewModel> getReviews() {
        return reviewRepository.findAll();
    }

    public ReviewModel update(ReviewModel model) throws NoFoundException {
        if (model.getId() != 0) {
            Optional<ReviewModel> opt = reviewRepository.findById(model.getId());
            if(!opt.isPresent()){
                throw new NoFoundException("You are try to update a record that does not exist.");
            }
        }
        return reviewRepository.save(model);
    }

    public void deleteReviewsById(long id) throws Exception {
        Optional<ReviewModel> opt = reviewRepository.findById(id);
        if (!opt.isPresent()) {
            throw new NoFoundException("Review Id " + id + " was not found");
        }
        reviewRepository.deleteById(id);
    }

     public void deleteReview(ReviewModel review) throws Exception {
        Optional<ReviewModel> opt = reviewRepository.findById(review.getId());
        if (!opt.isPresent()) {
            throw new NoFoundException("Review " +review + " was not found");
        }
        reviewRepository.delete(review);
    }
}
