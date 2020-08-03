package com.movies.ratingdataservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rating_info")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Rating {

    @Id
    private Long movieid;
    private int rating;

    public Rating(){

    }

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
