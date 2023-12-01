package org.wecancodeit.review.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.review.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    
}
