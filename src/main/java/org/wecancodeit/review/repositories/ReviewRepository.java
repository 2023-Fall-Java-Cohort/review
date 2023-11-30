package org.wecancodeit.review.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.review.models.ReviewModel;

public interface ReviewRepository extends CrudRepository<ReviewModel,Long> {
    
}
