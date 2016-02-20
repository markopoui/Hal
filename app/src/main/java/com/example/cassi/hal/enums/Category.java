package com.example.cassi.hal.enums;

/**
 * Created by cassi on 14-02-16.
 */
public enum Category {
    MOVIE("Movies", "Film"),
    TV("Tv", "Série TV"),
    GAME("Games", "Jeux Vidéo"),
    BOOK("Books", "Livre"),
    MUSIC("Music", "Musique"),
    ANIME("Anime", "Animation"),
    APPS("Apps", "Application"),
    XXX("XXX", "Porn");

    private final String categoryName;
    private final String t411CatName;

    Category(String category, String t411Cat) {
        this.categoryName = category;
        this.t411CatName = t411Cat;
    }

    public String getCategory() {
        return categoryName;
    }

    public String getT411CatName() {
        return t411CatName;
    }
}
