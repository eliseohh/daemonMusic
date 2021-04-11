package com.daemongear.beta.data.repositories;

import com.daemongear.beta.domain.entity.UrlBody;
import org.springframework.data.repository.CrudRepository;

public interface UrlDAO extends CrudRepository<UrlBody, Long> {
    boolean existsByUrl(String url);
}
