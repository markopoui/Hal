package com.example.cassi.hal;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.cassi.hal.model.KickassResult;
import com.example.cassi.hal.model.MyApiFilmResult;
import com.example.cassi.hal.model.T411Token;
import com.example.cassi.hal.model.T411TorrentItem;
import com.example.cassi.hal.model.TmdbResult;
import com.example.cassi.hal.retrofit.RetrofitManager;
import com.example.cassi.hal.utils.RegexUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;

public class TorrentParsingTestCase extends AndroidTestCase {
    private final String TAG = "TorrentParsingTestCase";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetT411Token() {
        T411Token token = null;
        String user = "mouton159753";
        String pass = "paulinemdp";

        try {
            Call<T411Token> call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.t411_api_base_url)).getT411Token(user, pass);
            token = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(token);
    }

    public void testGetT411Top100() {
        List<String> names = new ArrayList<>();
        List<T411TorrentItem> result = null;
        String token = "99591834:47:a36fbc5c03353bf5441e8fc1c2777bee";

        try {
            Call<List<T411TorrentItem>> call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.t411_api_base_url)).getT411Top100(token);
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(result);

        for(T411TorrentItem item : result){
            names.add(RegexUtils.getCleanedTorrentName(item.getName()));
        }

        assertNotNull(names);
    }

    public void testGetKickassResult() {
        KickassResult result = null;

        try {
            Call<KickassResult> call = RetrofitManager.getInstance().getRetrofitService("https://kat.cr/").getTestResult("new");
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(result);
    }

    public void testGetKickassNews() {
        KickassResult result = null;

        try {
            Call<KickassResult> call = RetrofitManager.getInstance().getRetrofitService("https://kat.cr/").getTestResult("new");
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(result);
    }

    public void testMyApiFilms() {
        MyApiFilmResult result = null;
        String token = "3ee35cd3-2fe8-4a6b-9ccb-feca730b5795";
        try {
            Call<MyApiFilmResult> call = RetrofitManager.getInstance().getRetrofitService("http://www.myapifilms.com").getFilmByTitle("fr-be", "Le seigneur des anneaux", token);
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        assertNotNull(result);
    }

    public void testTmdbMovie() {

        List<TmdbResult> names = new ArrayList<>();
        List<T411TorrentItem> result = null;
        String token = "99591834:47:a36fbc5c03353bf5441e8fc1c2777bee";

        try {
            Call<List<T411TorrentItem>> call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.t411_api_base_url)).getT411Top100(token);
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(result);

        for(T411TorrentItem item : result){

            TmdbResult t = null;
            String apiKey = "52f01d7a4b07a4a0af3c95efa94a1c03";
            try {
                Call<TmdbResult> call = RetrofitManager.getInstance().getRetrofitService("https://api.themoviedb.org").getTMDBMovieByTitle(RegexUtils.getCleanedTorrentName(item.getName()), "fr", apiKey);
                t = call.execute().body();
                names.add(t);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            assertNotNull(t);
        }
    }
}

