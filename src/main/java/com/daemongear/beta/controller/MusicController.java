package com.daemongear.beta.controller;

import com.daemongear.beta.dao.UrlDAO;
import com.daemongear.beta.dto.UrlBody;
import com.daemongear.beta.service.UrlCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/music")
@RestController
public class MusicController {

    @Autowired
    private UrlDAO urlDAO;

    @Autowired
    UrlCheck urlCheck;

    @RequestMapping("/test")
    public String testApi(){
        return "works";
    }

    @PostMapping("/receive")
    public ResponseEntity addUrl(@RequestBody UrlBody urlBody){

        urlBody.setDownloaded(false);

        if (urlCheck.ytUrlIsValid(urlBody.getUrl())) {
            urlDAO.save(urlBody);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

    }
}
