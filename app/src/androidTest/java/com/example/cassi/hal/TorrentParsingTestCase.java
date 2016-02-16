package com.example.cassi.hal;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.cassi.hal.model.KickassResult;
import com.example.cassi.hal.model.T411Result;
import com.example.cassi.hal.retrofit.RetrofitManager;

import retrofit.Call;

public class TorrentParsingTestCase extends AndroidTestCase {
    private final String TAG = "TorrentParsingTestCase";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetT411Token() {
        T411Result token = null;
        String user = "";
        String pass = "";

        try {
            Call<T411Result> call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.t411_api_base_url)).getT411Token(user, pass);
            token = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(token);
    }

    public void testGetT411Top100() {
        T411Result result = null;

        try {
            Call<T411Result> call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.t411_api_base_url)).getT411Top100();
            result = call.execute().body();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        assertNotNull(result);
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
}

