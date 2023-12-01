package org.wecancodeit.review.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.review.NoFoundException;
import org.wecancodeit.review.models.ReviewModel;
import org.wecancodeit.review.services.ReviewService;

@RestController
@RequestMapping({ "/api/review", "/api/Review", "/API/Review" })
public class ReviewController {
    private ReviewService reviewService;

    private static final Logger LOGGER = Logger.getLogger(ReviewController.class.getName());

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
        LOGGER.info("ReviewController started");
    }

    public <T> long getCount(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).count();
    }

    @GetMapping
    public ResponseEntity<?> getReviews() {

        Iterable<ReviewModel> models = reviewService.getReviews();
        LOGGER.info("ReviewController Get All Reviews requested " + getCount(models));
        return ResponseEntity.ok(models);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable Long id) throws NoFoundException {
        LOGGER.info("ReviewController Get by id Reviews requested");
        try {
            return ResponseEntity.ok(reviewService.getReviewsById(id));
        } catch (NoFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("An error occurred: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewsById(@PathVariable Long id) throws Exception {
        LOGGER.info("ReviewController Delete by id Reviews requested");
        try {
            reviewService.deleteReviewsById(id);
            return ResponseEntity.ok("Deleted Id " + id);
        } catch (NoFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("An error occurred: " + ex.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A bad error occurred: " + e.getMessage());
        }

    }
}
