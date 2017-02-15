/*
 * Copyright 2015 Andy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tanghong.recyclerviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


/**
 * 定义了子View的布局的RecyclerView.Adapter扩展类
 *
 * @author tanhong
 */
class CleverRecyclerViewAdapterProxy<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private RecyclerViewPager mRecyclerViewPager;
    private RecyclerView.Adapter<VH> mAdapter;
    private int mVisibleChildCount;

    public CleverRecyclerViewAdapterProxy(RecyclerViewPager recyclerViewPager, RecyclerView.Adapter<VH> adapter) {
        mAdapter = adapter;
        mVisibleChildCount = 1;
        mRecyclerViewPager = recyclerViewPager;
        setHasStableIds(mAdapter.hasStableIds());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
        View itemView = holder.itemView;
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams() == null ? new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) : itemView.getLayoutParams();
        if (mRecyclerViewPager.getLayoutManager().canScrollHorizontally()) {
            layoutParams.width = (mRecyclerViewPager.getWidth() - mRecyclerViewPager.getPaddingLeft() - mRecyclerViewPager.getPaddingRight()) / mVisibleChildCount;
        }
        if (mRecyclerViewPager.getLayoutManager().canScrollVertically()) {
            layoutParams.width = (mRecyclerViewPager.getWidth() - mRecyclerViewPager.getPaddingLeft() - mRecyclerViewPager.getPaddingRight());
            layoutParams.height = (mRecyclerViewPager.getHeight() - mRecyclerViewPager.getPaddingTop() - mRecyclerViewPager.getPaddingBottom()) / mVisibleChildCount;
        }
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        mAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    public RecyclerView.Adapter<VH> getAdapter() {
        return mAdapter;
    }

    public void setVisibleChildCount(int visibleChildCount) {
        mVisibleChildCount = visibleChildCount;
    }

}
