package com.example.cassi.hal.retrofit;

import com.example.cassi.hal.model.KickassResult;
import com.example.cassi.hal.model.T411Result;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.Url;

public interface RetrofitService {

    @GET("json.php")
    Call<KickassResult> getTestResult(@Query("q") String search) throws Exception;

    @Headers("Authorization: 99591834:44:3d872d582fe2eeb49c0f13edf1141c15")
    @GET("torrents/details/5406314")
    Call<T411Result> getT411Top100() throws Exception;

    @FormUrlEncoded
    @POST("auth")
    Call<T411Result> getT411Token(@Field("username") String user, @Field("password") String password) throws Exception;
}
