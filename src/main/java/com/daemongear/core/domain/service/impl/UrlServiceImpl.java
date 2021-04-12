package com.daemongear.core.domain.service.impl;

import com.daemongear.core.data.repositories.UrlDAO;
import com.daemongear.core.domain.entity.UrlBody;
import com.daemongear.core.domain.service.UrlService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
@Log4j2
public class UrlServiceImpl implements UrlService {

    private static final String YOUTUBE_PATTERN = "^((?:https?:)?//)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(/(?:[\\w\\-]+\\?v=|embed/|v\\/)?)([\\w\\-]+)(\\S+)?$";
    private final UrlDAO urlDAO;

    public UrlServiceImpl(UrlDAO urlDAO) {
        this.urlDAO = urlDAO;
    }

    @Override
    @Transactional
    public Mono<ResponseEntity<HttpStatus>> saveUrl(String url) {
        Mono<ResponseEntity<HttpStatus>> response = Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
        Pattern p = Pattern.compile(YOUTUBE_PATTERN);
        if (this.isValidUrl(p, log.getMessageFactory(), url)) {
            if (!urlDAO.existsByUrl(url)) {
                UrlBody urlBody = UrlBody.builder()
                        .url(url)
                        .build();
                urlDAO.save(urlBody);
                response = Mono.just(ResponseEntity.status(HttpStatus.OK).build());
            } else {
                response = Mono.just(ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
            }
        }

        return response;
    }

    public Flux<UrlBody> all() {
        return urlDAO.findAll();
    }
}
