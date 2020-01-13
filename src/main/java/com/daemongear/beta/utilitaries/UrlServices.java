package com.daemongear.beta.utilitaries;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlServices {

    public synchronized static boolean ytUrlIsValid(String url){

        Pattern p = Pattern.compile("^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$");

        Matcher m = p.matcher(url);

        if (m.find()) {
            return true;
        }

        return false;
    }

    public synchronized static boolean downloadFile(String url) throws IOException {

        final String ytCMD, formatCon, path;
        Path pathl = Paths.get(String.format("%s/%s", System.getProperty("user.home"), "daemonMusic"));
        Runtime rt = Runtime.getRuntime();
        path = String.format("%s/%s",System.getProperty("user.home"),"daemonMusic");
        Process proc;

        ytCMD = "youtube-dl -x --audio-format mp3";
        formatCon = String.format("%s %s",ytCMD , url);

        if (!Files.exists(pathl)) {
            new File(path).mkdir();
        }

        proc = rt.exec(formatCon,null,new File(String.format("%s/%s",System.getProperty("user.home"),"daemonMusic")));
        
        if (proc.exitValue() != 0)
            return false;

        return true;
    }
}
