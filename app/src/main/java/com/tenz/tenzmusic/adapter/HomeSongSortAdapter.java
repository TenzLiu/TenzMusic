package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.entity.SongSheetResponse;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GlideUtil;

import java.util.List;

public class HomeSongSortAdapter extends BaseQuickAdapter<SongSheetResponse.InfoBean> {

    public HomeSongSortAdapter(Context context, int layoutResID, List<SongSheetResponse.InfoBean> data) {
        super(context, layoutResID, data);
    }

    @Override
    public void convert(BaseViewHolder holder, SongSheetResponse.InfoBean item) {
        LinearLayout ll_container = (LinearLayout) holder.getView(R.id.ll_container);
        ImageView iv_image = (ImageView) holder.getView(R.id.iv_image);
        TextView tv_intro = (TextView) holder.getView(R.id.tv_intro);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iv_image.getLayoutParams();
        layoutParams.height = (DisplayUtil.getWindowWidth() - DisplayUtil.dp2px(15) * 2 - DisplayUtil.dp2px(10) * 2) / 3;

        if((holder.getAdapterPosition() + 1) > 3){
            initPadding(ll_container,holder.getAdapterPosition(),DisplayUtil.dp2px(10));
        }else{
            initPadding(ll_container,holder.getAdapterPosition(),0);
        }
        iv_image.setLayoutParams(layoutParams);

        GlideUtil.loadImage(mContext,item.getImgurl().replace("{size}","400"),iv_image);
        tv_intro.setText(item.getRankname());
    }

    /**
     * 设置padding
     * @param ll_container
     * @param position
     * @param paddingTop
     */
    private void initPadding(LinearLayout ll_container, int position, int paddingTop){
        if((position + 1) % 3 == 1){
            //左边item
            ll_container.setPadding(0,paddingTop,DisplayUtil.dp2px(5),0);
        }else if((position + 1) % 3 == 0){
            //右边item
            ll_container.setPadding(DisplayUtil.dp2px(5),paddingTop,0,0);
        }else{
            //中间item
            ll_container.setPadding(DisplayUtil.dp2px(5),paddingTop,DisplayUtil.dp2px(5),0);
        }
    }

}
