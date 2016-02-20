package com.example.cassi.hal.retrofit;

import com.example.cassi.hal.model.KickassResult;
import com.example.cassi.hal.model.MyApiFilmResult;
import com.example.cassi.hal.model.T411Token;
import com.example.cassi.hal.model.KickassTorrentItem;
import com.example.cassi.hal.model.T411TorrentItem;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RetrofitService {

    @GET("json.php")
    Call<KickassResult> getTestResult(@Query("q") String search) throws Exception;

    @GET("/torrents/top/100")
    Call<List<T411TorrentItem>> getT411Top100(@Header("Authorization") String token) throws Exception;

    @FormUrlEncoded
    @POST("auth")
    Call<T411Token> getT411Token(@Field("username") String user, @Field("password") String password) throws Exception;

    @GET("imdb/idIMDB")
    Call<MyApiFilmResult> getFilmByTitle(@Query("language") String language, @Query("title") String title, @Query("token") String token) throws Exception;
}
