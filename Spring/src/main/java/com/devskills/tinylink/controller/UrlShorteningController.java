package com.devskills.tinylink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.devskills.tinylink.service.UrlShorteningService;

@RestController
public class UrlShorteningController {
    
    @Autowired
    private UrlShorteningService service;

    @PostMapping("/tinylink")
    public String shortenUrl(@RequestBody String longUrl) {
        return service.shortenUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectUrl(@PathVariable String shortUrl) {
        String longUrl = service.getOriginalUrl(shortUrl);
        if (longUrl != null)
            return new RedirectView(longUrl);
        else 
            return new RedirectView("www.practicaldeveloper.com");
    }
}
