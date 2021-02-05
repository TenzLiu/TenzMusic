package com.tenz.tenzmusic.widget.music;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.widget.image.ShapeImageView;


/**
 * 音乐播放栏控件
 */
public class MusicPlayBar extends RelativeLayout {

    LinearLayout ll_content;
    ShapeImageView iv_music_play_song_image;
    TextView tv_music_play_song_name;
    TextView tv_music_play_singer;
    ImageView iv_music_play_controller;
    ImageView iv_music_play_song_list;

    private Context mContext;
    private OnMusicPlayBarClickListener mOnMusicPlayBarClickListener;

    public void setmOnMusicPlayBarClickListener(OnMusicPlayBarClickListener mOnMusicPlayBarClickListener) {
        this.mOnMusicPlayBarClickListener = mOnMusicPlayBarClickListener;
    }

    public MusicPlayBar(Context context) {
        this(context, null);
        mContext = context;
    }

    public MusicPlayBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_music_play, this, true);
        ll_content = inflate.findViewById(R.id.ll_content);
        iv_music_play_song_image = inflate.findViewById(R.id.iv_music_play_song_image);
        tv_music_play_song_name = inflate.findViewById(R.id.tv_music_play_song_name);
        tv_music_play_singer = inflate.findViewById(R.id.tv_music_play_singer);
        iv_music_play_controller = inflate.findViewById(R.id.iv_music_play_controller);
        iv_music_play_song_list = inflate.findViewById(R.id.iv_music_play_song_list);
        iv_music_play_song_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnMusicPlayBarClickListener){
                    mOnMusicPlayBarClickListener.toMusicPlay();
                }
            }
        });
        ll_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnMusicPlayBarClickListener){
                    mOnMusicPlayBarClickListener.toMusicPlay();
                }
            }
        });
        iv_music_play_controller.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnMusicPlayBarClickListener){
                    mOnMusicPlayBarClickListener.musicPlayControl();
                }
            }
        });
        iv_music_play_song_list.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnMusicPlayBarClickListener){
                    mOnMusicPlayBarClickListener.musicPlayList();
                }
            }
        });
    }

    /**
     * 设置歌曲图片
     * @param image
     */
    public void setSongImage(String image){
        GlideUtil.loadImage(mContext,image,GlideUtil.mLogoRequestOptions,iv_music_play_song_image);
    }

    /**
     * 设置歌曲图片
     * @param imageRes
     */
    public void setSongImage(int imageRes){
        iv_music_play_controller.setImageResource(imageRes);
    }

    /**
     * 设置歌曲名称
     * @param songName
     */
    public void setSongName(String songName){
        tv_music_play_song_name.setText(songName);
    }

    /**
     * 设置歌手名称
     * @param singer
     */
    public void setSinger(String singer){
        tv_music_play_singer.setText(singer);
    }

    /**
     * 设置音乐播放控制图标(播放、暂停、停止)
     * @param imageRes
     */
    public void setMusicPlayControlImage(int imageRes){
        iv_music_play_controller.setImageResource(imageRes);
    }

    public interface OnMusicPlayBarClickListener{
        void toMusicPlay();
        void musicPlayControl();
        void musicPlayList();
    }

}
