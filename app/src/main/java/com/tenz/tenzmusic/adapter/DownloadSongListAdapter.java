package com.tenz.tenzmusic.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.AbsEntity;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.inf.IEntity;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.base.BaseViewHolder;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ResourceUtil;
import com.tenz.tenzmusic.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DownloadSongListAdapter extends BaseQuickAdapter<AbsEntity> {

    private Map<String, Integer> mPositions = new ConcurrentHashMap<>();

    public DownloadSongListAdapter(Context context, int layoutResID, List<AbsEntity> data) {
        super(context, layoutResID, data);
        int i = 0;
        for (AbsEntity entity : data) {
            mPositions.put(getKey(entity), i);
            i++;
        }
    }

    @Override
    public void convert(BaseViewHolder holder, AbsEntity entity) {
        TextView tv_name = (TextView) holder.getView(R.id.tv_name);
        ProgressBar pb_download = (ProgressBar) holder.getView(R.id.pb_download);
        TextView tv_current_size = (TextView) holder.getView(R.id.tv_current_size);
        TextView tv_total_size = (TextView) holder.getView(R.id.tv_total_size);
        TextView tv_speed = (TextView) holder.getView(R.id.tv_speed);
        TextView tv_status = (TextView) holder.getView(R.id.tv_status);

        pb_download.setVisibility(View.VISIBLE);
        tv_current_size.setVisibility(View.VISIBLE);
        tv_speed.setVisibility(View.VISIBLE);

        String statusString = "完成";
        int resColor = R.color.color_gray;
        switch (entity.getState()){
            case IEntity.STATE_WAIT:
                statusString = "等待中";
                resColor = R.color.color_gray;
                break;
            case IEntity.STATE_OTHER:
            case IEntity.STATE_FAIL:
                statusString = "开始";
                resColor = R.color.color_yellow;
                break;
            case IEntity.STATE_STOP:
                statusString = "恢复";
                resColor = R.color.color_blue;
                break;
            case IEntity.STATE_PRE:
            case IEntity.STATE_POST_PRE:
            case IEntity.STATE_RUNNING:
                statusString = "暂停";
                resColor = R.color.color_red;
                break;
            case IEntity.STATE_COMPLETE:
                statusString = "完成";
                resColor = R.color.color_green;
                pb_download.setProgress(100);
                pb_download.setVisibility(View.GONE);
                tv_current_size.setVisibility(View.GONE);
                tv_speed.setVisibility(View.GONE);
                break;
        }
        long totalSize = entity.getFileSize();
        long currentSize = entity.getCurrentProgress();
        int progress = totalSize == 0 ? 0 : (int) (currentSize * 100 / totalSize);

        tv_name.setText(((DownloadEntity) entity).getFileName());
        pb_download.setProgress(progress);
        tv_current_size.setText(StringUtil.formatFileSize(currentSize) + "/");
        tv_total_size.setText(StringUtil.formatFileSize(totalSize));
        tv_speed.setText(StringUtil.formatFileSize(entity.getSpeed()) + "/s");
        tv_status.setText(statusString);
        tv_status.setTextColor(ResourceUtil.getColor(resColor));

        tv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (entity.getState()) {
                    case IEntity.STATE_WAIT:
                    case IEntity.STATE_OTHER:
                    case IEntity.STATE_FAIL:
                    case IEntity.STATE_STOP:
                    case IEntity.STATE_PRE:
                    case IEntity.STATE_POST_PRE:
                        if (entity.getId() < 0) {
                            start(entity);
                        } else {
                            resume(entity);
                        }
                        break;
                    case IEntity.STATE_RUNNING:
                        stop(entity);
                        break;
                    case IEntity.STATE_COMPLETE:
                        LogUtil.e("任务已完成");
                        break;
                }
            }
        });
    }

    /**
     * 重新开始下载任务
     * @param entity
     */
    public void resume(AbsEntity entity) {
        Aria.download(mContext).load(entity.getId()).resume(true);
    }

    /**
     * 开始下载任务
     * @param entity
     */
    public void start(AbsEntity entity) {
        Aria.download(mContext).load(entity.getKey()).create();
    }

    /**
     * 停止下载任务
     * @param entity
     */
    public void stop(AbsEntity entity) {
        if (!(entity != null && entity.getId() != -1)) {
            return;
        }
        Aria.download(mContext).load(entity.getId()).stop();
    }

    /**
     * 删除下载任务
     * @param entity
     */
    public void cancel(AbsEntity entity) {
        if (!(entity != null && entity.getId() != -1)) {
            return;
        }
        Aria.download(mContext).load(entity.getId()).cancel(true);
    }

    /**
     * 更新positions
     */
    public void updatePositions(List<AbsEntity> data){
        mPositions.clear();
        int i = 0;
        for (AbsEntity entity : data) {
            mPositions.put(getKey(entity), i);
            i++;
        }
    }

    /**
     * 更新状态
     * @param entity
     */
    public synchronized void updateState(AbsEntity entity) {
        if (entity.getState() == IEntity.STATE_CANCEL) {
            mData.remove(entity);
            mPositions.clear();
            int i = 0;
            for (AbsEntity entity_1 : mData) {
                mPositions.put(getKey(entity_1), i);
                i++;
            }
            LogUtil.e("mData:----"+mData.size());
            LogUtil.e("mPositions:----"+mPositions.size());
            notifyDataSetChanged();
        } else {
            int position = indexItem(getKey(entity));
            if (position == -1 || position >= mData.size()) {
                return;
            }
            mData.set(position, entity);
            notifyItemChanged(position);
        }
    }

    /**
     * 获取key
     * @param entity
     * @return
     */
    private String getKey(AbsEntity entity) {
        return ((DownloadEntity) entity).getUrl();
    }

    /**
     * 更新进度
     */
    public synchronized void setProgress(AbsEntity entity) {
        String url = entity.getKey();
        int position = indexItem(url);
        if (position == -1 || position >= mData.size()) {
            return;
        }
        mData.set(position, entity);
        notifyItemChanged(position, entity);
    }

    /**
     * 获取position
     * @param url
     * @return
     */
    private synchronized int indexItem(String url) {
        Set<String> keys = mPositions.keySet();
        for (String key : keys) {
            if (key.equals(url)) {
                return mPositions.get(key);
            }
        }
        return -1;
    }

}
