package com.daemongear.beta.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlServices {

    public boolean ytUrlIsValid(String url){

        Pattern p = Pattern.compile("^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$");

        Matcher m = p.matcher(url);

        if (m.find()) {
            return true;
        }

        return false;
    }

    public boolean generateFile(String url) throws FileNotFoundException {

        final String OS = System.getProperty("os.name").toLowerCase();

        File file = null;

        if (OS.contains("win")) {
            file = new File("c:/%HOMEPATH%/output.bat") ;
        } else {
            file = new File("$HOME/music/output.sh");
        }

        FileOutputStream fos = new FileOutputStream(file);
        if (fos.equals(null) ) {
            throw new FileNotFoundException("output file not found");
        } else {
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
            System.out.println("youtube-dl  " + url);

            return true;
        }
    }

}
