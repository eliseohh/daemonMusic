package com.daemongear.core.domain.service;

import com.daemongear.core.domain.entity.UrlBody;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UrlService
 */
public interface UrlService {
    Mono<ResponseEntity<HttpStatus>> saveUrl(String url);
    Flux<UrlBody> all();
    default boolean isValidUrl(Pattern pattern, Logger log, String url) {
        log.info("Validating url {} \n", url);
        Matcher m = pattern.matcher(url);

        return m.find();
    }
}