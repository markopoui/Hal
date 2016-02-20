package com.example.cassi.hal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cassi on 20-02-16.
 */
public class TmdbResult implements Serializable {
    int pages;
    List<Result> results;

    public int getPages() {
        return pages;
    }

    public List<Result> getResults() {
        return results;
    }

    public class Result {
        @SerializedName("poster_path")
        String posterPath;
        boolean adult;
        String overview;
        @SerializedName("release_date")
        String releaseDate;
        int id;
        @SerializedName("original_title")
        String originalTitle;
        @SerializedName("original_language")
        String originalLanguage;
        @SerializedName("backdrop_path")
        String backdropPath;
        @SerializedName("vote_average")
        float voteAverage;

        public String getBackdropPath() {
            return backdropPath;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getOverview() {
            return overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getId() {
            return id;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public float getVoteAverage() {
            return voteAverage;
        }
    }
}
