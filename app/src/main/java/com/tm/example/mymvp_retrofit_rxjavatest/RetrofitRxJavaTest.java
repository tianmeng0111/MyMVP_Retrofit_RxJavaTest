package com.tm.example.mymvp_retrofit_rxjavatest;

import com.google.gson.GsonBuilder;
import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tian on 2017/2/13.
 */

public class RetrofitRxJavaTest {

    public void main() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build();
        IRetrofitService service = retrofit.create(IRetrofitService.class);
        Observable<BookBean> observable = service.getSearchBook2("金瓶梅", null, 0, 1);
        observable.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        //所有事件都完成，可以做些操作。。。
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace(); //请求过程中发生错误
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        //这里的book就是我们请求接口返回的实体类
                    }
                });

    }
}
