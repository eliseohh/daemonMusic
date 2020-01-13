package com.daemongear.beta.controller;

import com.daemongear.beta.dao.UrlDAO;
import com.daemongear.beta.dto.UrlBody;
import com.daemongear.beta.utilitaries.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RequestMapping("/music")
@RestController
public class MusicController {

    org.slf4j.Logger logger = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private UrlDAO urlDAO;

    @GetMapping("/test")
    public String testApi(){
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        
        return System.getProperty("os.name");
    }

    @PostMapping("/receive")
    public ResponseEntity addUrl(@RequestBody UrlBody urlBody) throws IOException {

        urlBody.setDownloaded(false);

        if (UrlServices.ytUrlIsValid(urlBody.getUrl())) {
            if (UrlServices.downloadFile(urlBody.getUrl()) && !urlDAO.existsByUrl(urlBody.getUrl())) {
                urlDAO.save(urlBody);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_MODIFIED);
            }
        }

        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

    }
}
