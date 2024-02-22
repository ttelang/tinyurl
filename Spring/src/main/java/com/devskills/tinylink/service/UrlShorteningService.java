package com.devskills.tinylink.service;

import java.util.Base64;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devskills.tinylink.model.UrlMapping;
import com.devskills.tinylink.repository.UrlMappingRepository;

@Service
public class UrlShorteningService {
    @Autowired
    private UrlMappingRepository repository;

    public String shortenUrl(String longUrl) {
        Random random = new Random();

        // Generate a random long between 0 and Long.MAX_VALUE
        long randomNumber = Math.abs(random.nextLong());

        // Convert the long to bytes
        byte[] bytes = Long.toUnsignedString(randomNumber).getBytes();

        // Encode the bytes to Base64
        String base64String = Base64.getEncoder().encodeToString(bytes);
        String shortUrl = base64String.substring(0, 6);
        repository.save(new UrlMapping(shortUrl, longUrl));
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

}
