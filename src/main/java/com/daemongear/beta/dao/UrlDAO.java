package com.daemongear.beta.dao;

import com.daemongear.beta.dto.UrlBody;
import org.springframework.data.repository.CrudRepository;

public interface UrlDAO extends CrudRepository<UrlBody, Long> {
}
