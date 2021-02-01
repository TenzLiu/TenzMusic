package com.tenz.tenzmusic.widget.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tenz.tenzmusic.R;


/**
 * titlebar控件
 */
public class TitleBar extends RelativeLayout {

    private LinearLayout ll_back;
    private TextView tv_title;
    private TextView tv_more;
    private Context mContext;
    private OnTitleBarClickListener mOnTitleBarClickListener;

    public void setmOnTitleBarClickListener(OnTitleBarClickListener mOnTitleBarClickListener) {
        this.mOnTitleBarClickListener = mOnTitleBarClickListener;
    }

    public TitleBar(Context context) {
        this(context, null);
        mContext = context;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.view_title_bar, this, true);
        ll_back = inflate.findViewById(R.id.ll_back);
        tv_title = inflate.findViewById(R.id.tv_title);
        tv_more = inflate.findViewById(R.id.tv_more);
        ll_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnTitleBarClickListener){
                    mOnTitleBarClickListener.back();
                }
            }
        });
        tv_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnTitleBarClickListener){
                    mOnTitleBarClickListener.more();
                }
            }
        });
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public void setBackGone(){
        ll_back.setVisibility(GONE);
    }

    public void setTitleMore(String more){
        tv_more.setVisibility(VISIBLE);
        tv_more.setText(more);
    }

    public interface OnTitleBarClickListener{
        void back();
        void more();
    }


}
