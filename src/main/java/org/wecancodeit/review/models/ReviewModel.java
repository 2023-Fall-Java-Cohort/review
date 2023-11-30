package org.wecancodeit.review.models;

import jakarta.persistence.*;

@Entity
public class ReviewModel {
    @Id
    @GeneratedValue
    private long id;

    private String reviewedBy;
    private int rating;
    
    public ReviewModel() {
    }

    public ReviewModel( String reviewedBy, int rating) {
        this.reviewedBy = reviewedBy;
        this.rating = rating;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public int getRating() {
        return rating;
    }

    
}
