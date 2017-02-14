package com.tm.example.mymvp_retrofit_rxjavatest.service.view;

import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;

/**
 * Created by Tian on 2017/2/13.
 */

public interface IView {

    void onSuccess(BookBean book);
    void onFail(String error);
}
