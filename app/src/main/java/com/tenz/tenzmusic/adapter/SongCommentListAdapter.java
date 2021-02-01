package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.SongCommentResponse;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.TextViewUtil;

import java.util.List;

public class SongCommentListAdapter extends BaseQuickAdapter<SongCommentResponse.ListBean> {

    public SongCommentListAdapter(Context context, int layoutResID, List<SongCommentResponse.ListBean> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, SongCommentResponse.ListBean item) {
        ImageView iv_image = (ImageView) holder.getView(R.id.iv_image);
        TextView tv_name = (TextView) holder.getView(R.id.tv_name);
        TextView tv_time = (TextView) holder.getView(R.id.tv_time);
        TextView tv_content = (TextView) holder.getView(R.id.tv_content);
        LinearLayout ll_show_more = (LinearLayout) holder.getView(R.id.ll_show_more);
        TextView tv_show_more = (TextView) holder.getView(R.id.tv_show_more);
        ImageView iv_show_more = (ImageView) holder.getView(R.id.iv_show_more);
        ll_show_more.setVisibility(View.GONE);
        tv_content.setMaxLines(Integer.MAX_VALUE);
        GlideUtil.loadImage(mContext,item.getUser_pic().replace("{size}","400"),iv_image);
        tv_name.setText(item.getUser_name());
        tv_time.setText(item.getAddtime());
        tv_content.setText(item.getContent());

        int textViewLines = TextViewUtil.getTextViewLines(tv_content, DisplayUtil.getWindowWidth());
        if(textViewLines > 3){
            tv_content.setMaxLines(3);
            ll_show_more.setVisibility(View.VISIBLE);
        }else{
            ll_show_more.setVisibility(View.GONE);
        }
        ll_show_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_content.getMaxLines() > 3){
                    tv_content.setMaxLines(3);
                    tv_show_more.setText("更多");
                    iv_show_more.setImageResource(R.drawable.down_gray);
                }else{
                    tv_content.setMaxLines(Integer.MAX_VALUE);
                    tv_show_more.setText("收起");
                    iv_show_more.setImageResource(R.drawable.up_gray);
                }
            }
        });
    }

}
