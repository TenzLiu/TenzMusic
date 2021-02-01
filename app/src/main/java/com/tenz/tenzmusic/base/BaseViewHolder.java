package com.tenz.tenzmusic.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;
    private View convertView;


    public BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
        convertView = itemView;
    }

    //根据id检索获得该View对象
    private <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, CharSequence charSequence) {
        TextView textView = retrieveView(viewId);
        textView.setText(charSequence);
        return this;
    }

    public View getView(int viewId) {
        return retrieveView(viewId);
    }

}
