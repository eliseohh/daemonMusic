package com.daemongear.core.domain.service;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UrlService
 */
public interface UrlService {
    ResponseEntity<Void> saveUrl(String url);
    boolean downloadFile(String url);

    default boolean isValidUrl(Pattern pattern, Logger log, String url) {
        log.info("Validating url {} \n", url);
        Matcher m = pattern.matcher(url);

        return m.find();
    }
}