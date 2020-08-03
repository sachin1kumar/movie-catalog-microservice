package com.movies.moviecatalogservice.controllers;

import com.movies.moviecatalogservice.models.CatalogItem;
import com.movies.moviecatalogservice.models.MovieInfo;
import com.movies.moviecatalogservice.models.Rating;
import com.movies.moviecatalogservice.repositories.CatalogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class CatalogItemController {

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userid}")
    public CatalogItem getCatalog(@PathVariable Long userid){

        //TODO
        //get all rated movie IDs.
        Rating rating = restTemplate.getForObject("http://localhost:8083/ratingsdata/123", Rating.class);

        //For each movie id, call movie info service and get details.
        MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieid(), MovieInfo.class);

        System.out.println("Rating: "+rating.getMovieid()+", MovieInfo:"+movieInfo.getMovieid());
        //put them all together.

        return catalogItemRepository.getOne(movieInfo.getMovieid());
    }
}
