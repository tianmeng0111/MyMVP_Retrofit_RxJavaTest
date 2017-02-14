package com.tm.example.mymvp_retrofit_rxjavatest;

import com.google.gson.GsonBuilder;
import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tian on 2017/2/13.
 */

public class RetrofitTest {
    private static final String TAG = "RetrofitTest";

    public void demoCode() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        IRetrofitService iRetrofitService = retrofit.create(IRetrofitService.class);
        Call<BookBean> call = iRetrofitService.getSearchBook("金瓶梅", null, 0, 1);
        call.enqueue(new Callback<BookBean>() {
            @Override
            public void onResponse(Call<BookBean> call, Response<BookBean> response) {
                BookBean bookBean = response.body();
            }

            @Override
            public void onFailure(Call<BookBean> call, Throwable t) {

            }
        });
    }

}
