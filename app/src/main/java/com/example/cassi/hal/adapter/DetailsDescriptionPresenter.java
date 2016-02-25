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

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;

import com.example.cassi.hal.model.Movie;
import com.example.cassi.hal.model.T411TorrentItem;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {

    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object item) {
        T411TorrentItem torrentItem = (T411TorrentItem) item;

        if (torrentItem != null) {
            viewHolder.getTitle().setText(torrentItem.getName());
            viewHolder.getSubtitle().setText(torrentItem.getCategoryName());
            viewHolder.getBody().setText(torrentItem.getUsername());
        }
    }
}
