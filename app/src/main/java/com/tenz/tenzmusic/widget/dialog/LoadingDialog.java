package com.tenz.tenzmusic.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.tenz.tenzmusic.R;


/**
 * Author: VVP
 * Time: 2018/11/19 16:06.
 * Desc:
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // attributes.windowAnimations = R.style.SelectHeadDialogAnimation;
        getWindow().setAttributes(attributes);
        getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void show() {
        if (!this.isShowing())
            super.show();
    }

    @Override
    public void dismiss() {
        if (this.isShowing())
            super.dismiss();
    }
}
