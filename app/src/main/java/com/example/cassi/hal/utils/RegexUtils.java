package com.example.cassi.hal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cassi on 18-02-16.
 */
public class RegexUtils {


    public static String getCleanedTorrentName(String torrentName){
        String name = torrentName;
        Pattern pattern = Pattern.compile("\\(.+?\\)|(\\(19\\d{2}|20\\d{2}\\))|19\\d{2}|20\\d{2}|x264|x265|1080p|720p");
        Matcher matcher = pattern.matcher(torrentName);
        // Check all occurrences
        while (matcher.find()) {
            name = torrentName.substring(0, matcher.start());
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end());
            System.out.println(" Found: " + matcher.group());
            break;
        }
        return name;
    }
}
