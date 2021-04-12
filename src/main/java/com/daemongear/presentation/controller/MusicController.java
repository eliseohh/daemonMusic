package com.daemongear.presentation.controller;

import com.daemongear.core.domain.entity.UrlBody;
import com.daemongear.core.domain.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/download/music")
@RestController
public class MusicController {

    private final UrlService urlService;

    public MusicController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/youtube/{url}")
    public Mono<ResponseEntity<HttpStatus>> addUrl(@PathVariable final String url) {
        return urlService.saveUrl(url);
    }

    @GetMapping("/youtube/all")
    public Flux<UrlBody> getAll() {
        return urlService.all();
    }
}
