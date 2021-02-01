package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.VideoBean;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.StringUtil;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<VideoBean> {

    public VideoAdapter(Context context, int layoutResID, List<VideoBean> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, VideoBean item) {
        LinearLayout ll_container = (LinearLayout) holder.getView(R.id.ll_container);
        ImageView iv_image = (ImageView) holder.getView(R.id.iv_image);
        TextView tv_play_count = (TextView) holder.getView(R.id.tv_play_count);
        TextView tv_video_duration = (TextView) holder.getView(R.id.tv_video_duration);
        TextView tv_video_desc = (TextView) holder.getView(R.id.tv_video_desc);

        //设置了阴影自动有padding
//        if(holder.getAdapterPosition() % 2 == 0){
//            ll_container.setPadding(0,DisplayUtil.dp2px(5),DisplayUtil.dp2px(5),DisplayUtil.dp2px(5));
//        }else{
//            ll_container.setPadding(DisplayUtil.dp2px(5),DisplayUtil.dp2px(5),0,DisplayUtil.dp2px(5));
//        }

        GlideUtil.loadImage(mContext,item.getAlbum_cover().replace("{size}","400"),iv_image);
        tv_play_count.setText("" + StringUtil.formatNum(String.valueOf(item.getPlaycount()),false));
        tv_video_duration.setText("" + StringUtil.stringForTime(new Long(item.getDuration()).intValue()));
        tv_video_desc.setText(item.getTitle() + " | " + item.getDescription());
    }

}
