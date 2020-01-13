package com.daemongear.beta.service.impl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.daemongear.beta.service.UrlService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    public boolean ytUrlIsValid(String url) {
        logger.info("Validating url {} \n With pattern", url);
        Pattern p = Pattern.compile("^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$");

        Matcher m = p.matcher(url);

        if (m.find()) {
            return true;
        }

        return false;
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
            e.printStackTrace();
        }
        if (proc != null)
            if (proc.exitValue() != 0)
                return false;
        
        
        return true;
    }
}
