package org.wecancodeit.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.review.models.ReviewModel;
import org.wecancodeit.review.services.ReviewService;

@Component
public class Populator implements CommandLineRunner {
    private ReviewService reviewService;
   
   
    public Populator(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    public void run(String... args) throws Exception {
       ReviewModel model1 = new ReviewModel("Review 1", 1);
       reviewService.update(model1);
       ReviewModel model2 = new ReviewModel("Review 2", 1);
       reviewService.update(model2);
       ReviewModel model3 = new ReviewModel("Review 3", 1);
       reviewService.update(model3);
    }


   
    
    
}
