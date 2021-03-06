/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.cassi.hal.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.cassi.hal.R;
import com.example.cassi.hal.enums.Category;
import com.example.cassi.hal.model.T411TorrentItem;
import com.example.cassi.hal.model.TmdbResult;
import com.example.cassi.hal.retrofit.RetrofitManager;
import com.example.cassi.hal.utils.RegexUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
 * A CardPresenter is used to generate Views and bind Objects to them on demand. 
 * It contains an Image CardView
 */
public class CardPresenter extends Presenter {
    private static final String TAG = "CardPresenter";

    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 176;
    private static int sSelectedBackgroundColor;
    private static int sDefaultBackgroundColor;
    private Drawable mDefaultCardImage;
    private Context mContext;

    public CardPresenter(Context context){
        mContext = context;
    }

    private static void updateCardBackgroundColor(ImageCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");

        sDefaultBackgroundColor = parent.getResources().getColor(R.color.default_background);
        sSelectedBackgroundColor = parent.getResources().getColor(R.color.selected_background);
        mDefaultCardImage = parent.getResources().getDrawable(R.drawable.movie);

        ImageCardView cardView = new ImageCardView(parent.getContext()) {
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(cardView, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        T411TorrentItem torrentItem = (T411TorrentItem) item;
        ImageCardView cardView = (ImageCardView) viewHolder.view;

        Log.d(TAG, "onBindViewHolder");
        cardView.setTitleText(torrentItem.getName());
        cardView.setContentText(getSeedAndLeech(torrentItem));
        cardView.getMainImageView().setLayoutParams(new android.support.v17.leanback.widget.BaseCardView.LayoutParams(CARD_WIDTH, android.support.v17.leanback.widget.BaseCardView.LayoutParams.WRAP_CONTENT));
        cardView.setLayoutParams(new FrameLayout.LayoutParams(CARD_WIDTH, FrameLayout.LayoutParams.WRAP_CONTENT));
        cardView.getMainImageView().setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.poster_placeholder));
        if(torrentItem.getBackgroundUrl() != null) {
            Glide.with(mContext)
                    .load(mContext.getString(R.string.themoviedb_poster_url) + torrentItem.getPosterUrl())
                    .error(ContextCompat.getDrawable(mContext, R.drawable.poster_placeholder))
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.poster_placeholder))
                    .into(cardView.getMainImageView());
        }else {
            if(torrentItem.getCategoryName().equals(Category.MOVIE.getT411CatName()) || torrentItem.getCategoryName().equals(Category.ANIME.getT411CatName()))
                getMovieTMDBdetail(cardView, torrentItem, Category.MOVIE);
            if(torrentItem.getCategoryName().equals(Category.TV.getT411CatName()))
                getMovieTMDBdetail(cardView, torrentItem, Category.TV);
        }
    }

    private Spannable getSeedAndLeech(T411TorrentItem item){
        String text = "Seed : " + String.valueOf(item.getSeeders()) + " Leech : " + String.valueOf(item.getLeechers());
        Spannable spannable = new SpannableString(text);

        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.seedColor)), text.indexOf(String.valueOf(item.getSeeders())), text.indexOf(String.valueOf(item.getSeeders())) + String.valueOf(item.getSeeders()).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.leechColor)), text.lastIndexOf(String.valueOf(item.getLeechers())), text.lastIndexOf(String.valueOf(item.getLeechers())) + String.valueOf(item.getLeechers()).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannable;
    }

    private void getMovieTMDBdetail(final ImageCardView cardView, final T411TorrentItem item, Category category) {
        try {
            TmdbResult result = null;
            Call<TmdbResult> call = null;
            if(category == Category.MOVIE) {
                call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.themoviedb_api_base_url)).getTMDBMovieByTitle(RegexUtils.getCleanedTorrentName(item.getName()), "fr", mContext.getString(R.string.themoviedb_key));
            }else if(category == Category.TV){
                call = RetrofitManager.getInstance().getRetrofitService(mContext.getString(R.string.themoviedb_api_base_url)).getTMDBSerieByTitle(RegexUtils.getCleanedTorrentName(item.getName()), "fr", mContext.getString(R.string.themoviedb_key));
            }
            assert call != null;
            call.enqueue(new Callback<TmdbResult>() {
                @Override
                public void onResponse(Response<TmdbResult> response, Retrofit retrofit) {
                    if(response.body().getResults() != null && response.body().getResults().size() > 0 && cardView.getTitleText().equals(item.getName())) {
                        Glide.with(mContext)
                                .load(mContext.getString(R.string.themoviedb_poster_url) + response.body().getResults().get(0).getPosterPath())
                                .error(ContextCompat.getDrawable(mContext, R.drawable.poster_placeholder))
                                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.poster_placeholder))
                                .into(cardView.getMainImageView());
                        item.setBackgroundUrl(response.body().getResults().get(0).getBackdropPath());
                        item.setPosterUrl(response.body().getResults().get(0).getPosterPath());
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        // Remove references to images so that the garbage collector can free up memory
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
