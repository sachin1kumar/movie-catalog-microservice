package com.movies.moviecatalogservice.controllers;

import com.movies.moviecatalogservice.models.CatalogItem;
import com.movies.moviecatalogservice.models.MovieInfo;
import com.movies.moviecatalogservice.models.Rating;
import com.movies.moviecatalogservice.repositories.CatalogItemRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class CatalogItemController {

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userid}")
    @HystrixCommand(fallbackMethod = "getFallBackMethod")
    public CatalogItem getCatalog(@PathVariable Long userid){

        //get all rated movie IDs.
        //using Rest template.
        Rating rating = restTemplate.getForObject("http://rating-data-service/ratingsdata/123", Rating.class);
        //using web client builder.
        /*Rating rating = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/ratingsdata/123")
                .retrieve()
                .bodyToMono(Rating.class)
                .block();*/

        //For each movie id, call movie info service and get details.
        MovieInfo movieInfo = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieid(), MovieInfo.class);
        //using web client builder.
        /*MovieInfo movieInfo = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/movies/"+rating.getMovieid())
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();*/
        System.out.println("Rating: "+rating.getMovieid()+", MovieInfo:"+movieInfo.getMovieid());
        //put them all together.

        return catalogItemRepository.getOne(movieInfo.getMovieid());
    }

    public CatalogItem getFallBackMethod(@PathVariable Long userid) {
        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setDescription("No data right now");
        return catalogItem;
    }
}
