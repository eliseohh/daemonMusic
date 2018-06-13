package com.daemongear.beta.controller;

import com.daemongear.beta.dao.UrlDAO;
import com.daemongear.beta.dto.UrlBody;
import com.daemongear.beta.service.UrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RequestMapping("/music")
@RestController
public class MusicController {

    @Autowired
    private UrlDAO urlDAO;

    @Autowired
    UrlServices urlServices;

    @RequestMapping("/test")
    public String testApi(){
        return System.getProperty("os.name");
    }

    @PostMapping("/receive")
    public ResponseEntity addUrl(@RequestBody UrlBody urlBody) throws IOException {

        urlBody.setDownloaded(false);

        if (urlServices.ytUrlIsValid(urlBody.getUrl())) {
            if (urlServices.downloadFile(urlBody.getUrl()) && !urlDAO.existsByUrl(urlBody.getUrl())) {
                urlDAO.save(urlBody);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_MODIFIED);
            }
        }

        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

    }
}
