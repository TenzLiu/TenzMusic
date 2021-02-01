package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.SongSheetResponse;
import com.tenz.tenzmusic.util.GlideUtil;

import java.util.List;

public class SongSortAdapter extends BaseQuickAdapter<SongSheetResponse.InfoBean> {

    public SongSortAdapter(Context context, int layoutResID, List<SongSheetResponse.InfoBean> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, SongSheetResponse.InfoBean item) {
        ImageView iv_image = (ImageView) holder.getView(R.id.iv_image);
        TextView tv_title = (TextView) holder.getView(R.id.tv_title);
        TextView tv_song_one = (TextView) holder.getView(R.id.tv_song_one);
        TextView tv_song_two = (TextView) holder.getView(R.id.tv_song_two);
        TextView tv_song_three = (TextView) holder.getView(R.id.tv_song_three);
        GlideUtil.loadImage(mContext,item.getImgurl().replace("{size}","400"),iv_image);
        tv_title.setText(item.getRankname());
        List<SongSheetResponse.InfoBean.SonginfoBean> songinfoBeanList = item.getSonginfo();
        if(null != songinfoBeanList){
            if(songinfoBeanList.size() >= 3){
                tv_song_one.setText(songinfoBeanList.get(0).getSongname());
                tv_song_two.setText(songinfoBeanList.get(1).getSongname());
                tv_song_three.setText(songinfoBeanList.get(2).getSongname());
            }else if(songinfoBeanList.size() >= 2){
                tv_song_one.setText(songinfoBeanList.get(0).getSongname());
                tv_song_two.setText(songinfoBeanList.get(1).getSongname());
                tv_song_three.setText("张国荣-十号风球");
            }else if(songinfoBeanList.size() >= 1){
                tv_song_one.setText(songinfoBeanList.get(0).getSongname());
                tv_song_two.setText("张敬轩-俏郎君");
                tv_song_three.setText("张国荣-十号风球");
            }else{
                tv_song_one.setText("陈奕迅-是但求其爱");
                tv_song_two.setText("张敬轩-俏郎君");
                tv_song_three.setText("张国荣-十号风球");
            }
        }else{
            tv_song_one.setText("陈奕迅-是但求其爱");
            tv_song_two.setText("张敬轩-俏郎君");
            tv_song_three.setText("张国荣-十号风球");
        }

    }

}
