package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.util.StringUtil;

import java.util.List;

public class LocalSongListAdapter extends BaseQuickAdapter<PlaySongBean> {

    private Option mOption;

    public LocalSongListAdapter(Context context, int layoutResID, List<PlaySongBean> data, Option option) {
        super(context, layoutResID, data);
        this.mOption = option;
    }

    @Override
    public void convert(BaseViewHolder holder, PlaySongBean item) {
        ImageView iv_video_play = (ImageView) holder.getView(R.id.iv_video_play);
        ImageView iv_dot = (ImageView) holder.getView(R.id.iv_dot);
        TextView tv_order_number = (TextView) holder.getView(R.id.tv_order_number);
        TextView tv_song_name = (TextView) holder.getView(R.id.tv_song_name);
        TextView tv_singer = (TextView) holder.getView(R.id.tv_singer);
        tv_order_number.setText("" + (holder.getAdapterPosition() + 1));
        tv_song_name.setText(item.getSong_name());
        tv_singer.setText(item.getAuthor_name().split(" ")[0]);
        if(StringUtil.isEmpty(item.getMvhash())){
            iv_video_play.setVisibility(View.GONE);
        }else{
            iv_video_play.setVisibility(View.VISIBLE);
        }
        iv_video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOption != null){
                    mOption.playVideo(holder.getAdapterPosition());
                }
            }
        });
        iv_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOption != null){
                    mOption.dotClick(holder.getAdapterPosition());
                }
            }
        });

    }

    public interface Option{
        void playVideo(int position);
        void dotClick(int position);
    }

}
