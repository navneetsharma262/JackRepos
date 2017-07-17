package com.jackrepos.ui.common;

/**
 * Created by Navneet on 13-07-2017.
 */

public interface MvpPresenter<V> {

    void attachView(V view);
    void detachView();

}