package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.PlaySongBean;

import java.util.List;

public class PopSongListAdapter extends BaseQuickAdapter<PlaySongBean> {

    private Option mOption;

    public PopSongListAdapter(Context context, int layoutResID, List<PlaySongBean> data, Option option) {
        super(context, layoutResID, data);
        this.mOption = option;
    }

    @Override
    public void convert(BaseViewHolder holder, PlaySongBean item) {
        ImageView iv_delete = (ImageView) holder.getView(R.id.iv_delete);
        TextView tv_song_name = (TextView) holder.getView(R.id.tv_song_name);
        TextView tv_singer = (TextView) holder.getView(R.id.tv_singer);
        tv_song_name.setText(item.getSong_name());
        tv_singer.setText(item.getAuthor_name());
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOption != null){
                    mOption.delete(holder.getAdapterPosition());
                }
            }
        });

    }

    public interface Option{
        void delete(int position);
    }

}
