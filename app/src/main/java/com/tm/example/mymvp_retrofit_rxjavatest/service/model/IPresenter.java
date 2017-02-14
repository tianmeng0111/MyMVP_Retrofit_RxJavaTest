package com.tm.example.mymvp_retrofit_rxjavatest.service.model;

import android.view.View;

import com.tm.example.mymvp_retrofit_rxjavatest.service.view.IView;

/**
 * Created by Tian on 2017/2/13.
 */

public interface IPresenter {

    void onCreate();

    void onStart();

    void onStop();

    void attachView(IView iView);

}
