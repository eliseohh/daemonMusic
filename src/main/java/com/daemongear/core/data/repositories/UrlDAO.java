package com.daemongear.core.data.repositories;

import com.daemongear.core.domain.entity.UrlBody;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlDAO extends ReactiveCrudRepository<UrlBody, Long> {
    boolean existsByUrl(String url);
}
