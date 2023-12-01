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
    // Service that does the work
    private ReviewService reviewService;

    // Logging Service
    private static final Logger LOGGER = Logger.getLogger(ReviewController.class.getName());

    /**
     * Consturctor
     * 
     * @param reviewService
     */
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

    //  @DeleteMapping("")
    // public ResponseEntity<?> deleteReviewsById() throws Exception {
    //     return ResponseEntity
    //                 .status(HttpStatus.NOT_FOUND)
    //                 .body("An error occurred: can not have a empty ID to delete Review");
    // }
    @DeleteMapping("")
     public ResponseEntity<?> deleteReview(@RequestBody ReviewModel review) {
        ResponseEntity<?> response;
        try {
            reviewService.deleteReview(review);
            response = ResponseEntity.ok("Deleted " + review);
        } catch (NoFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("An error occurred: " + ex.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A bad error occurred: " + e.getMessage());
        }
        return response;
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewsById(@PathVariable Long id) throws Exception {
        LOGGER.info("ReviewController Delete by id Reviews requested");
        ResponseEntity<?> response;
        try {
            reviewService.deleteReviewsById(id);
            response = ResponseEntity.ok("Deleted Id " + id);
        } catch (NoFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("An error occurred: " + ex.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A bad error occurred: " + e.getMessage());
        }
        return response;
    }

    @PostMapping()
    public ResponseEntity<?> createReview(@RequestBody ReviewModel review) {
        ReviewModel returnValue;
        ResponseEntity<?> entity;
        try {
            returnValue = reviewService.update(review);
            if (returnValue == null) {
                throw new Exception("Review Not updated");
            }
            entity = ResponseEntity.ok(returnValue);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            entity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A bad error occurred: " + e.getMessage());
        }
        return entity;
    }

    @PutMapping()
    public ResponseEntity<?> updateReview(@RequestBody ReviewModel review) throws Exception {
        if (review.getId() == 0) {
            throw new Exception("Review Not updated, can not insert a new record");
        }
        return createReview(review);
    }

}
