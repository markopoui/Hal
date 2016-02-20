package com.example.cassi.hal.model;

import java.util.List;

/**
 * Created by cassi on 18-02-16.
 */
public class MyApiFilmResult {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        List<Movie> movies;

        public List<Movie> getMovies() {
            return movies;
        }
    }
}
