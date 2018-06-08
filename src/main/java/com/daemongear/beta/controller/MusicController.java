package com.daemongear.beta.controller;

import com.daemongear.beta.dao.UrlDAO;
import com.daemongear.beta.dto.UrlBody;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/music")
@RestController
public class MusicController {

    @Autowired
    private UrlDAO urlDAO;

    @RequestMapping("/test")
    public String testApi(){
        return "works";
    }

    @PostMapping("/receive")
    public void addUrl(@RequestBody UrlBody urlBody){

        urlBody.setDownloaded(false);
        urlDAO.save(urlBody);
    }
}
