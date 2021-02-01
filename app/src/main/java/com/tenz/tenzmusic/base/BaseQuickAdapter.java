package com.tenz.tenzmusic.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.tenz.tenzmusic.R;

import java.util.List;

public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public Context mContext;
    public int mLayoutResID;
    public List<T> mData;
    public OnItemClickListener mOnItemClickListener = null;
    public OnItemLongClickListener mOnItemLongClickListener = null;

    // 普通的item ViewType
    private static final int TYPE_ITEM = 1;
    // 空布局的ViewType
    private static final int TYPE_EMPTY = 2;
    // 是否显示空布局，默认显示
    private boolean showEmptyView = true;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public BaseQuickAdapter(Context context, int layoutResID, List<T> data) {
        mContext = context;
        this.mLayoutResID = layoutResID;
        this.mData = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder;
        if (viewType == TYPE_EMPTY) {
            // 创建空布局item
            return new BaseViewHolder(getEmptyView(parent));
        } else {
            // 创建普通的item
            final View itemView = LayoutInflater.from(mContext).inflate(mLayoutResID, parent, false);
            holder = new BaseViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // 如果是空布局item，不需要绑定数据
        if (isEmptyPosition(position)) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnItemClickListener){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(null != mOnItemLongClickListener){
                    mOnItemLongClickListener.onItemLongClick(position);
                }
                return false;
            }
        });
        convert(holder, mData.get(position));
    }

    public abstract void  convert(BaseViewHolder holder, T item);

    @Override
    public int getItemCount() {
        // 判断数据是否空，如果没有数据，并且需要显示空布局，就返回1。
        int count = mData != null ? mData.size() : 0;
        if (count > 0) {
            return count;
        } else if (showEmptyView) {
            // 显示空布局
            return 1;
        } else {
            return 0;
        }
    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmptyPosition(position)) {
            // 空布局
            return TYPE_EMPTY;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 获取空布局
     */
    private View getEmptyView(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_empty_no_message_data,
                parent, false);
        return view;
    }

    /**
     * 判断是否是空布局
     */
    public boolean isEmptyPosition(int position) {
        int count = mData != null ? mData.size() : 0;
        return position == 0 && showEmptyView && count == 0;
    }

    /**
     * 设置空布局显示。默认不显示
     */
    public void showEmptyView(boolean isShow) {
        if (isShow != showEmptyView) {
            showEmptyView = isShow;
            notifyDataSetChanged();
        }
    }

    /**
     * 是否显示空布局
     * @return
     */
    public boolean isShowEmptyView() {
        return showEmptyView;
    }

}
