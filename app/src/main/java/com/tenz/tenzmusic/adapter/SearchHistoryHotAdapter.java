package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;

import java.util.List;

public class SearchHistoryHotAdapter extends BaseQuickAdapter<String> {

    public SearchHistoryHotAdapter(Context context, int layoutResID, List<String> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, String item) {
        holder.setText(R.id.tv_name,item);
    }

}
