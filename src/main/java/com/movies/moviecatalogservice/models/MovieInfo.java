package com.movies.moviecatalogservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "movie_info")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class MovieInfo {

    @Id
    private Long movieid;
    private String name;

    public MovieInfo() {

    }

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
