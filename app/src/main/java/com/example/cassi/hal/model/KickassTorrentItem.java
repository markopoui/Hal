package com.example.cassi.hal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cassi on 14-02-16.
 */
public class KickassTorrentItem implements Serializable {
    private String title;
    private String category;
    private String link;
    private String guid;
    private String pubDate;
    private String torrentLink;
    private int files;
    private int comments;
    private String hash;
    private int peers;
    private int seeds;
    private int leechs;
    private long size;
    private int votes;
    private int verified;

    public String getFixedTitle(){
        String value = title;
        Pattern pattern = Pattern.compile("\\(.+?\\)|\\(19\\d{2}|20\\d{2}\\)|19\\d{2}|20\\d{2}");
        Matcher matcher = pattern.matcher(title);
        // Check all occurrences
        while (matcher.find()) {
            value = title.substring(0, matcher.start());

            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end());
            System.out.println(" Found: " + matcher.group());
        }

        return value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTorrentLink() {
        return torrentLink;
    }

    public void setTorrentLink(String torrentLink) {
        this.torrentLink = torrentLink;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public int getLeechs() {
        return leechs;
    }

    public void setLeechs(int leechs) {
        this.leechs = leechs;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
}
