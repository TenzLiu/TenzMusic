package com.tenz.tenzmusic.util;

import android.app.Activity;
import android.view.WindowManager;

public class AppUtil {

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha 外部透明度
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
