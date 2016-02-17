package com.example.cassi.hal.model;

import com.example.cassi.hal.enums.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassi on 14-02-16.
 */
public class KickassResult implements Serializable {
    private String title;
    private String link;
    private String language;
    private int ttl;
    private String totalResults;
    private List<KickassTorrentItem> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<KickassTorrentItem> getList() {
        return list;
    }

    public void setList(List<KickassTorrentItem> list) {
        this.list = list;
    }

    public List<KickassTorrentItem> getCategory(Category cat){
        List<KickassTorrentItem> result = new ArrayList<>();
        for (KickassTorrentItem torrentItem : list){
            if(torrentItem.getCategory().equals(cat.getCategory())){
                result.add(torrentItem);
            }
        }

        return result;
    }
}
