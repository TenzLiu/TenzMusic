package com.tenz.tenzmusic.http;

import android.accounts.NetworkErrorException;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ResourceUtil;
import com.tenz.tenzmusic.util.StringUtil;
import com.tenz.tenzmusic.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Author: fwp
 * Date: 2019-04-09
 * Description: BaseObserver 封装观察者(请求错误，成功，token过期处理)
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.isSuccess()) {
            try {
                onSuccess(tBaseResponse.getData());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e(e.toString());
            }
        } else {
            try {
                onError(StringUtil.isEmpty(tBaseResponse.getError()) ?
                        ResourceUtil.getString(R.string.connect_fail) : tBaseResponse.getError());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e(e.toString());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e(e.toString());
        try {
            connectError();
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof SocketTimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof HttpException
                    || e instanceof UnknownHostException) {
                onError(ResourceUtil.getString(R.string.net_enable));
            } else {
                onError(ResourceUtil.getString(R.string.connect_fail));
            }
            onFinish();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 请求开始返回
     */
    protected abstract void onStart();

    /**
     * 请求数据成功返回
     * @param data
     * @throws Exception
     */
    protected abstract void onSuccess(T data) throws Exception;

    /**
     * 请求数据失败返回
     * @param message
     */
    protected void onError(String message) throws Exception {
        ToastUtil.showToast(message);
    }

    /**
     * 请求完成返回
     */
    protected abstract void onFinish();

    /**
     * 网络连接失败隐藏loading框处理
     * @throws Exception
     */
    protected void connectError() throws Exception {
        //RxBus发送通知
    }

}
