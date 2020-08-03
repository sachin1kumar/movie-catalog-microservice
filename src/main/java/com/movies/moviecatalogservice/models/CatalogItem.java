package com.movies.moviecatalogservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "catalog_items")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CatalogItem {

    @Id
    private Long userid;
    private String movie;
    private String description;
    private Integer rating;

    public CatalogItem(){

    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
