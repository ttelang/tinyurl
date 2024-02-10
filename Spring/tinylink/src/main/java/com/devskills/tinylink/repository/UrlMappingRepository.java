package com.devskills.tinylink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devskills.tinylink.model.UrlMapping;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {

    Optional<UrlMapping> findByShortUrl(String shortUrl);

}
