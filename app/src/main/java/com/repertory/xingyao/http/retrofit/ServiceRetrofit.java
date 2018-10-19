package com.repertory.xingyao.http.retrofit;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.repertory.xingyao.AndroidApplication;
import com.repertory.xingyao.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Title:ServiceRetrofit
 * @Package:com.repertory.xingyao.http.retrofit
 * @Description:
 * Retrofit结合并封装Okhttp,将api接口转化为java接口。并拦截封装、转换数据结构类型；
 * post 请求的资源大部分不会被缓存，get请求的资源会被全部缓存；
 * 所以对于要使用缓存的资源数据，请求方式最好使用 get 请求方式；
 * 添加缓存策略，方式有两种：1，request  2，response
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1910:17
 */
public final class ServiceRetrofit {

    private final static String TAG = "ServiceRetrofit";
    private final static String BASE_URL = "";
    /** 指定app缓存的大小 **/
    private final static long CACHE_SIZE = 50 * 1024 * 1024L;
    /** 指定默认的连接超时时间 **/
    private final static long OVER_TIME = 15L;
    /** 设缓存有效期为12h **/
    static final long CACHE_STALE_SEC = 12 * 60 * 60L;
    /** 设请求到的资源缓存有效期为2h **/
    static final long CACHE_STALE_NET_SEC = 2 * 60 * 60L;
    /** 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间 **/
    private static final String CACHE_CONTROL_CACHE = "public, only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /** 设置缓存以及超时时间 **/
    private static final String CACHE_CONTROL_NET = "public, max-age=" + CACHE_STALE_NET_SEC;

    protected static void build(){

        //配置缓存
        final Cache cache = new Cache(new File(AndroidApplication.getInstance().getCacheDir(), "Repertory-data"), CACHE_SIZE);

        //配置okhttp
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(OVER_TIME, TimeUnit.SECONDS)
                .addInterceptor(INTERCEPTOR_LOGGER)//打印请求的url信息
                .addInterceptor(INTERCEPTOR_CACHE)//配置缓存
                .addNetworkInterceptor(INTERCEPTOR_CACHE)
                .build();


        //配置okhttp到retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }


    /**
     * @Title:拦截器-INTERCEPTOR_LOGGER
     * @Description:重写Interceptor，实现拦截打印请求到的json数据；
     */
    private static final Interceptor INTERCEPTOR_LOGGER = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if(null != request && null != request.body()){
                request.body().writeTo(requestBuffer);
            }else {
                //打印输出"错误"的log日志
                Logger.e(TAG, "--> there was an error occurred with 'null == request.body()' <--");
            }
            //打印url信息
            Logger.d(TAG+"--->>>"+request.url() + (request.body() != null ?
                    "?" + parseParams(request.body(), requestBuffer) : "*NULL*"));
            final Response response = chain.proceed(request);
            return response;
        }
    };

    @NonNull
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }


    /**
     * @Title:拦截器-INTERCEPTOR_CACHE
     * @Description:重写Interceptor，配置缓存策略功能；
     * 首先，判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取
     */
    private static final Interceptor INTERCEPTOR_CACHE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if(!NetUtil.isNetworkAvailable(AndroidApplication.getInstance())){
                //如果当前网络无效不可用
                Logger.e(TAG, "--> the current network is not available. <--");
                //无网络可用则强制使用缓存
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response = chain.proceed(request);
            if(NetUtil.isNetworkAvailable(AndroidApplication.getInstance())){
                //网络可用
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                response = response.newBuilder()
                        //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
//                        .header("Cache-Control", request.cacheControl().toString())
                        .header("Cache-Control", CACHE_CONTROL_NET)
                        .build();

                return response;
            }

            //无网络使用缓存 只查询缓存而不会请求服务器  从缓存中返回资源
            response = response.newBuilder()
                    .header("Cache-Control", CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();

            return response;
        }
    };
}
