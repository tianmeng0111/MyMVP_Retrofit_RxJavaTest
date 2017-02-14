package com.tm.example.mymvp_retrofit_rxjavatest.service.presenter;

import android.content.Context;

import com.tm.example.mymvp_retrofit_rxjavatest.IRetrofitService;
import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;
import com.tm.example.mymvp_retrofit_rxjavatest.service.model.IPresenter;
import com.tm.example.mymvp_retrofit_rxjavatest.service.view.IBookView;
import com.tm.example.mymvp_retrofit_rxjavatest.service.view.IView;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Tian on 2017/2/13.
 */

public class BookPresenter implements IPresenter {

    private Context context;
    private IBookView iBookView;
    private BookBean book;

    private CompositeSubscription compositeSubscription;

    private IRetrofitService iRetrofitService;

    public BookPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        this.compositeSubscription = new CompositeSubscription();
        this.iRetrofitService = RetrofitHelper.getInstance(context).getService();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(IView iView) {
        iBookView = (IBookView) iView;
    }

    public void getSearchBooks(String name, String tag, int start, int count) {
        Observable<BookBean> observable = iRetrofitService.getSearchBook2(name, tag, start, count);
        Subscription subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                        if (iBookView != null) {
                            iBookView.onSuccess(book);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBookView != null) {
                            iBookView.onFail(e.toString());
                        }
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        book = bookBean;
                    }
                });
        compositeSubscription.add(subscribe);

    }
}
