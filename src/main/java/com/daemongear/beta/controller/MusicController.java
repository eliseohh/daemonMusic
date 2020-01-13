package com.daemongear.beta.controller;

import com.daemongear.beta.dao.UrlDAO;
import com.daemongear.beta.dto.UrlBody;
import com.daemongear.beta.service.UrlService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RequestMapping("/music")
@RestController
public class MusicController {

    Logger logger = LoggerFactory.getLogger(MusicController.class);

    private UrlDAO urlDAO;
    private UrlService urlService;

    public MusicController(UrlDAO urlDAO, UrlService urlService) {
        this.urlDAO = urlDAO;
        this.urlService = urlService;
    }

    @PostMapping("/receive")
    public ResponseEntity<Void> addUrl(@RequestBody final UrlBody urlBody) throws IOException {

        urlBody.setDownloaded(false);

        if (urlService.ytUrlIsValid(urlBody.getUrl())) {
            if (urlService.downloadFile(urlBody.getUrl()) && !urlDAO.existsByUrl(urlBody.getUrl())) {
                urlDAO.save(urlBody);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

    }
}
