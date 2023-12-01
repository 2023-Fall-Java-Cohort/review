package org.wecancodeit.review.models;

import jakarta.persistence.*;

@Entity
public class ReviewModel {
    @Id
    @GeneratedValue
    private long id;
    
    private int rating;
    
    @ManyToOne
    private UserModel user;

    public ReviewModel() {
    }

    public ReviewModel( UserModel reviewed, int rating) {
        this.rating = rating;
        this.user = reviewed;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getReviewedBy() {
        if(user != null){
            return user.getFirstName();
        }
        return null;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "ReviewModel [id=" + id + ", reviewedBy=" + getReviewedBy() + ", rating=" + rating + "]";
    }

    
}
