package com.tenz.tenzmusic.http;

/**
 * Author: fwp
 * Date: 2019-04-09
 * Description: 后台返回的数据格式 ,到时候根据实际情况作修改
 */

public class BaseResponse<T> {

    private int status;
    private String errcode;
    private String error;
    private T data;
    public static final int STATUS_SUCCESS = 1;//成功的status

    /**
     * 是否请求数据成功
     * @return
     */
    public boolean isSuccess(){
        return STATUS_SUCCESS == getStatus();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
