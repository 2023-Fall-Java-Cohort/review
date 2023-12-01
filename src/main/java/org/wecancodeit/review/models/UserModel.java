package org.wecancodeit.review.models;

import java.util.Collection;

import jakarta.persistence.*;

@Entity
public class UserModel {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String phone;
   
    @OneToMany(mappedBy = "user")
    public Collection<ReviewModel> reviews;

    public UserModel() {
    }

    public UserModel(String firstName, String phone) {
        this.firstName = firstName;
        this.phone = phone;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    
}
