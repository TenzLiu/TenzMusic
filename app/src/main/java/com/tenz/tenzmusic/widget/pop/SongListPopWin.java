package com.tenz.tenzmusic.widget.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.service.MusicService;
import com.tenz.tenzmusic.adapter.PopSongListAdapter;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.widget.dialog.ConfirmDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐列表popuwindow
 */
public class SongListPopWin extends PopupWindow {

    private Activity mActivity;
    private Context mContext;
    private PopSongListAdapter mPopSongListAdapter;
    private List<PlaySongBean> mPlaySongBeanList;
    private FragmentManager mFragmentManager;

    public SongListPopWin(Activity activity,Context context, List<PlaySongBean> playSongBeanList, FragmentManager fragmentManager) {
        super(activity);
        mActivity = activity;
        mContext = context;
        mPlaySongBeanList = new ArrayList<>();
        mPlaySongBeanList.addAll(playSongBeanList);
        mFragmentManager = fragmentManager;
        View view = LayoutInflater.from(activity).inflate(R.layout.popu_song_list, null);
        ImageView iv_music_play_model = view.findViewById(R.id.iv_music_play_model);
        TextView tv_music_play_model = view.findViewById(R.id.tv_music_play_model);
        ImageView iv_delete = view.findViewById(R.id.iv_delete);
        RecyclerView rv_song = view.findViewById(R.id.rv_song);
        TextView tv_close = view.findViewById(R.id.tv_close);

        rv_song.setLayoutManager(new LinearLayoutManager(mContext));
        mPopSongListAdapter = new PopSongListAdapter(mActivity, R.layout.item_pop_song_list, mPlaySongBeanList, new PopSongListAdapter.Option() {
            @Override
            public void delete(int position) {
                    ConfirmDialog.newInstance("提示","确定删除该歌曲？").setCancelConfirmOption(new ConfirmDialog.CancelConfirmOption() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        mPlaySongBeanList.remove(position);
                        mPopSongListAdapter.notifyDataSetChanged();
                        App.getApplication().getmMusicBinder().deleteMusic(position);
                        updateData(iv_music_play_model,tv_music_play_model);

                        DBManager.newInstance().playSongDao().deleteByHash(mPlaySongBeanList.get(position).getHash());
                    }
                }).setWidth(DisplayUtil.px2dp((int) (DisplayUtil.getWindowWidth() * 0.65)))
                        .show(mFragmentManager);
            }
        });
        mPopSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                App.getApplication().getmMusicBinder().playMusic(position);
                dismiss();
            }
        });
        rv_song.setAdapter(mPopSongListAdapter);

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog.newInstance("提示","确定删除全部？").setCancelConfirmOption(new ConfirmDialog.CancelConfirmOption() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        mPlaySongBeanList.clear();
                        mPopSongListAdapter.notifyDataSetChanged();
                        App.getApplication().getmMusicBinder().deleteMusicAll();
                        updateData(iv_music_play_model,tv_music_play_model);

                        for (PlaySongBean playSongBean: mPlaySongBeanList){
                            DBManager.newInstance().playSongDao().deleteByHash(playSongBean.getHash());
                        }
                    }
                }).setWidth(DisplayUtil.px2dp((int) (DisplayUtil.getWindowWidth() * 0.65)))
                        .show(mFragmentManager);
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        updateData(iv_music_play_model,tv_music_play_model);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setContentView(view);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0xb0000000));
        setAnimationStyle(R.style.popup_show_anim);

    }

    /**
     * 更新视图
     */
    private void updateData(ImageView iv_music_play_model, TextView tv_music_play_model){
        int playModel = App.getApplication().getmMusicBinder().getPlayModel();
        switch (playModel){
            case MusicService.PLAY_MODEL_REPEAT:
                iv_music_play_model.setImageResource(R.drawable.music_repeat_gray);
                tv_music_play_model.setText("循环播放(" + App.getApplication().getmMusicBinder().getPlaySongBeanList().size() + "首)");
                break;
            case MusicService.PLAY_MODEL_SINGLE:
                iv_music_play_model.setImageResource(R.drawable.music_single_gray);
                tv_music_play_model.setText("单曲播放(" + App.getApplication().getmMusicBinder().getPlaySongBeanList().size() + "首)");
                break;
            case MusicService.PLAY_MODEL_RANDOM:
                iv_music_play_model.setImageResource(R.drawable.music_random_gray);
                tv_music_play_model.setText("随机播放(" + App.getApplication().getmMusicBinder().getPlaySongBeanList().size() + "首)");
                break;
        }
    }

}
