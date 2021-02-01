package com.tenz.tenzmusic.http;

import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.app.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory sInstance;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    public static final String BASE_URL = "http://mobilecdnbj.kugou.com/api/";//分类，热门等
    public static final String BASE_URL_KUGOU = "https://wwwapi.kugou.com/";//歌曲详情
    public static final String BASE_URL_KUGOU_MV = "http://m.kugou.com/";//MV
    public static final String BASE_URL_KUGOU_SEARCH = "http://msearchcdn.kugou.com";//搜索
    public static final String BASE_URL_KUGOU_COMMENT = "http://m.comment.service.kugou.com";//评论
    private static final long DEFAULT_CONNECT_TIMEOUT = 30L;
    private static final long DEFAULT_WRITE_TIMEOUT = 30L;
    private static final long DEFAULT_READ_TIMEOUT = 30L;

    public static RetrofitFactory getInstance(){
        if(sInstance == null){
            synchronized (RetrofitFactory.class){
                if(sInstance == null){
                    sInstance = new RetrofitFactory();
                }
            }
        }
        return sInstance;
    }

    private RetrofitFactory() {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(new Cache(new File(App.getApplication().getCacheDir(),""),1024*1024*10))
                .addInterceptor(InterceptorUtil.getHeadInterceptor())
            .addInterceptor(InterceptorUtil.getLogInterceptor())
            .addInterceptor(InterceptorUtil.getCacheInterceptor())
            .addNetworkInterceptor(InterceptorUtil.getCacheInterceptor())
            .sslSocketFactory(new SSLSocketClient().getSSLSocketFactory())
            .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 创建API
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createApi(Class<T> tClass) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(tClass);
    }

    /**
     * 创建API
     *
     * @param baseUrl 多个BaseURL切换
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createApi(String baseUrl, Class<T> tClass) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(tClass);
    }

    /**
     * 返回retrofitApi
     * @return
     */
    public RetrofitApi retrofitApi(){
        return getInstance().createApi(RetrofitApi.class);
    }

}
