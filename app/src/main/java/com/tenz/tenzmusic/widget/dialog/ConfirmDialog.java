package com.tenz.tenzmusic.widget.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.app.App;


/**
 * Author: TenzLiu
 * Time: 2018/9/2 22:55
 * Desc:
 */

public class ConfirmDialog extends BaseDialog {

    private boolean isShowTitle;
    private boolean isShowCancel;
    private String title;
    private String content;
    private String cancel;
    private String confirm;
    private CancelConfirmOption mCancelConfirmOption;

    public ConfirmDialog setCancelConfirmOption(CancelConfirmOption cancelConfirmOption) {
        this.mCancelConfirmOption = cancelConfirmOption;
        return this;
    }

    public interface CancelConfirmOption{
        void cancel();
        void confirm();
    }

    public static ConfirmDialog newInstance(String title, String content) {
        return newInstance(true,true,title,content, App.getContext().getString(R.string.cancel),
                App.getContext().getString(R.string.confirm));
    }

    public static ConfirmDialog newInstance(boolean isShowCancel, String title, String content) {
        return newInstance(true,isShowCancel,title,content,App.getContext().getString(R.string.cancel),
                App.getContext().getString(R.string.confirm));
    }

    public static ConfirmDialog newInstance(boolean isShowCancel, String title, String content, String cancel, String confirm) {
        return newInstance(true,isShowCancel,title,content,confirm,cancel);
    }

    public static ConfirmDialog newInstance(boolean isShowTitle, boolean isShowCancel, String title, String content,
                                            String cancel, String confirm) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShowTitle",isShowTitle);
        bundle.putBoolean("isShowCancel",isShowCancel);
        bundle.putString("title",title);
        bundle.putString("content",content);
        bundle.putString("cancel",cancel);
        bundle.putString("confirm",confirm);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(bundle);
        return confirmDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(null != bundle){
            isShowTitle = bundle.getBoolean("isShowTitle");
            isShowCancel = bundle.getBoolean("isShowCancel");
            title = bundle.getString("title");
            content = bundle.getString("content");
            cancel = bundle.getString("cancel");
            confirm = bundle.getString("confirm");
        }
    }

    @Override
    public int setUpLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseDialog dialog) {
        if(!isShowTitle){
            holder.getView(R.id.tv_title).setVisibility(View.GONE);
        }
        if (!isShowCancel){
            holder.getView(R.id.tv_cancel).setVisibility(View.GONE);
        }
        holder.setText(R.id.tv_title,title);
        holder.setText(R.id.tv_content,content);
        holder.setText(R.id.tv_cancel,cancel);
        holder.setText(R.id.tv_confirm,confirm);
        holder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(null != mCancelConfirmOption){
                    mCancelConfirmOption.cancel();
                }
            }
        });
        holder.setOnClickListener(R.id.tv_confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(null != mCancelConfirmOption){
                    mCancelConfirmOption.confirm();
                }
            }
        });
    }

}
