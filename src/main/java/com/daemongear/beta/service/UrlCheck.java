package com.daemongear.beta.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlCheck {

    public boolean ytUrlIsValid(String url){

        Pattern p = Pattern.compile("^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$");

        Matcher m = p.matcher(url);

        if (m.find()) {
            return true;
        }

        return false;

    }

}
