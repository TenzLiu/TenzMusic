package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.SongBean;
import com.tenz.tenzmusic.util.GlideUtil;

import java.util.List;

public class HomeSongListAdapter extends BaseQuickAdapter<SongBean> {

    public HomeSongListAdapter(Context context, int layoutResID, List<SongBean> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, SongBean item) {
        ImageView iv_image = (ImageView) holder.getView(R.id.iv_image);
        TextView tv_song_name = (TextView) holder.getView(R.id.tv_song_name);
        TextView tv_singer = (TextView) holder.getView(R.id.tv_singer);
        GlideUtil.loadImage(mContext,item.getCover().replace("{size}","400"),iv_image);
        tv_song_name.setText(item.getRemark());
        tv_singer.setText(item.getFilename());
    }

}
