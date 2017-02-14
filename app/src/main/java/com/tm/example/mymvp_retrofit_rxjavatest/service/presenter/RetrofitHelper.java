package com.tm.example.mymvp_retrofit_rxjavatest.service.presenter;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.tm.example.mymvp_retrofit_rxjavatest.IRetrofitService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tian on 2017/2/13.
 */

public class RetrofitHelper {

    private Context context;
    OkHttpClient okHttpClient = new OkHttpClient();
    GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit retrofit;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    public RetrofitHelper(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        getData();
    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public IRetrofitService getService() {
        return retrofit.create(IRetrofitService.class);
    }
}
