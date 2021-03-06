package com.miuhouse.community.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by khb on 2015/8/24.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<T> mList;
    public Activity mContext;
    public LayoutInflater mLayoutInflater;
    public boolean isShowFooterView = false;

    public interface OnCheckmarkClickListener {
        void onCheckItemClick(int position);
    }

    public OnCheckmarkClickListener mOnCheckmstkItemClickListener;
//    public int listSize;

    public BaseRecyclerViewAdapter(Activity mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutInflater = mContext.getLayoutInflater();
    }

    public BaseRecyclerViewAdapter(Activity mContext, List<T> mList, OnCheckmarkClickListener mOnCheckmstkItemClickListener) {
        this.mOnCheckmstkItemClickListener = mOnCheckmstkItemClickListener;
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutInflater = mContext.getLayoutInflater();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void showFooterView(boolean isShowFooterView) {
        this.isShowFooterView = isShowFooterView;
        notifyDataSetChanged();
    }

}
