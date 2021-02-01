package com.tenz.tenzmusic.http;

import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.NetWorkUtil;
import com.tenz.tenzmusic.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class InterceptorUtil {

    /**
     * 头部拦截器
     * @return
     */
    public static Interceptor getHeadInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //处理业务逻辑，可以对header统一处理，涉及到header加密的也在此处理
                //在这里可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Content-Type", "application/json; charset=utf-8");
                Request build = builder.build();
                return chain.proceed(build);
            }
        };
    }

    /**
     * 日志拦截器
     * @return
     */
    public static Interceptor getLogInterceptor(){
        final Charset UTF8 = Charset.forName("UTF-8");
        return new Interceptor(){
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                RequestBody requestBody = request.body();
                ResponseBody responseBody = response.body();
                String responseBodyString = responseBody.string();
                String requestMessage;
                requestMessage = request.method() + ' ' + request.url();
                if (requestBody != null) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    requestMessage += "?\n" + buffer.readString(UTF8);
                }
                LogUtil.d("requestMessage:"+requestMessage);
                responseBodyString = StringUtil.handlerResponseString(responseBodyString);
                LogUtil.d("responseBodyString:"+responseBodyString);
                try {
                    JSONObject jsonObject = new JSONObject(responseBodyString);
                    int status = jsonObject.optInt("status");
                    if (BaseResponse.STATUS_SUCCESS == status){

                    }else{
                        jsonObject.put("data", new JSONObject());
                        responseBodyString = jsonObject.toString();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return response.newBuilder().body(ResponseBody.create(responseBody.contentType(),
                        responseBodyString.getBytes())).build();
            }
        };
    }

    /**
     * 缓存拦截器
     * @return
     */
    public static Interceptor getCacheInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // 有网络时 设置缓存超时时间1个小时
                int maxAge = 60 * 60;
                // 无网络时，设置超时为1天
                int maxStale = 60 * 60 * 24;
                Request request = chain.request();
                if(NetWorkUtil.isConnected(App.getContext())){
                    //有网络时只从网络获取
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
                }else{
                    //无网络时只从缓存中读取
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(request);
                if (NetWorkUtil.isConnected(App.getContext())) {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
    }

}
