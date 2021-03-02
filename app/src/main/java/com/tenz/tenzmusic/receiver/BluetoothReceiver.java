package com.tenz.tenzmusic.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.StringUtil;

/**
 * 蓝牙监听接收者（实现音乐的暂停与播放）
 */
public class BluetoothReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(!StringUtil.isEmpty(action)){
            switch (action){
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState){
                        case BluetoothAdapter.STATE_TURNING_ON:
                            LogUtil.e("蓝牙正在打开");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            LogUtil.e("蓝牙已经打开");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            LogUtil.e("蓝牙正在关闭");
                            break;
                        case BluetoothAdapter.STATE_OFF:
                            LogUtil.e("蓝牙已经关闭");
                            break;
                    }
                    break;
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    LogUtil.e("蓝牙设备已连接");
                    if(!App.getApplication().getmMusicBinder().getPlayState()){
                        App.getApplication().getmMusicBinder().start();
                    }
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    LogUtil.e("蓝牙设备已断开");
                    if(App.getApplication().getmMusicBinder().getPlayState()){
                        App.getApplication().getmMusicBinder().pause();
                    }
                    break;
            }
        }
    }

}
