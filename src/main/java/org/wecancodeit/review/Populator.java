package org.wecancodeit.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.review.models.*;
import org.wecancodeit.review.repositories.UserRepository;
import org.wecancodeit.review.services.ReviewService;

import jakarta.annotation.Resource;

@Component
public class Populator implements CommandLineRunner {
    private ReviewService reviewService;

    @Resource
    private UserRepository userRepository;


    public Populator(ReviewService reviewService,UserRepository userRepository ) {
        this.reviewService = reviewService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserModel user1 = new UserModel("Fred", "5748908765");
        userRepository.save(user1);
        UserModel user2 = new UserModel("BossMan", "5748908765");
        userRepository.save(user2);
        ReviewModel model1 = new ReviewModel(user1, 1);
        reviewService.update(model1);
        ReviewModel model2 = new ReviewModel(user2, 1);
        reviewService.update(model2);
        ReviewModel model3 = new ReviewModel(user1, 1);
        reviewService.update(model3);
    }

}
