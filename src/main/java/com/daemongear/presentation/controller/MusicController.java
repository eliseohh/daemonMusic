package com.daemongear.presentation.controller;

import com.daemongear.core.domain.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/download/music")
public class MusicController {

    private final UrlService urlService;

    public MusicController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/youtube/{url}")
    public ResponseEntity<Void> addUrl(@PathVariable final String url) {
        return  urlService.saveUrl(url);

    }
}
