package com.daemongear.presentation.controller;

import com.daemongear.core.domain.service.UrlService;
import com.daemongear.core.domain.entity.UrlBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/download/music")
@RestController
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
