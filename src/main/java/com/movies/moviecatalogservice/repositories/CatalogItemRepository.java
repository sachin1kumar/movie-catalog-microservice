package com.movies.moviecatalogservice.repositories;

import com.movies.moviecatalogservice.models.CatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
}
