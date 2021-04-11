package com.daemongear.core.domain.service.impl;

import com.daemongear.core.data.repositories.UrlDAO;
import com.daemongear.core.domain.entity.UrlBody;
import com.daemongear.core.domain.service.UrlService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Service
@Log4j2
public class UrlServiceImpl implements UrlService {

    private static final String YOUTUBE_PATTERN = "^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$";
    private final UrlDAO urlDAO;

    public UrlServiceImpl(UrlDAO urlDAO) {
        this.urlDAO = urlDAO;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> saveUrl(String url) {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Pattern p = Pattern.compile(YOUTUBE_PATTERN);
        if (this.isValidUrl(p, log.getMessageFactory(), url)) {
            if (!urlDAO.existsByUrl(url)) {
                UrlBody urlBody = new UrlBody();
                urlBody.setUrl(url);
                urlDAO.save(urlBody);
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }

        return responseEntity;
    }

    public boolean downloadFile(String url) {

        final String ytCMD, formatCon, path;
        Path pathl = Paths.get(String.format("%s/%s", System.getProperty("user.home"), "daemonMusic"));
        Runtime rt = Runtime.getRuntime();
        path = String.format("%s/%s", System.getProperty("user.home"), "daemonMusic");
        Process proc = null;

        ytCMD = "youtube-dl -x --audio-format mp3";
        formatCon = String.format("%s %s", ytCMD, url);

        if (!Files.exists(pathl)) {
            new File(path).mkdir();
        }
        try {
            proc = rt.exec(formatCon, null,
                    new File(String.format("%s/%s", System.getProperty("user.home"), "daemonMusic")));
        } catch (IOException e) {
            log.error(e);
        }

        return proc == null;

    }
}
