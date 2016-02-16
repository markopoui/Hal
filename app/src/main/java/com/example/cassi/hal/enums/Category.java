package com.example.cassi.hal.enums;

/**
 * Created by cassi on 14-02-16.
 */
public enum Category {
    MOVIE("Movies"),
    TV("Tv"),
    GAME("Games"),
    BOOK("Books"),
    MUSIC("Music"),
    ANIME("Anime"),
    APPS("Apps"),
    XXX("XXX");

    private final String categoryName;

    Category(String category) {
        this.categoryName = category;
    }

    public String getCategory() {
        return categoryName;
    }
}
