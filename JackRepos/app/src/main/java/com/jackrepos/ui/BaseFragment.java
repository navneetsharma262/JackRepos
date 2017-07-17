package com.jackrepos.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;

import com.jackrepos.R;
import com.jackrepos.ui.common.MvpView;
import com.jackrepos.ui.common.MvpPresenter;

/**
 * Created by Navneet on 13-07-2017.
 */
public class BaseFragment<T extends MvpPresenter> extends Fragment implements MvpView {

    protected T mPresenter;
    protected ProgressDialog mProgressDialog;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if(active) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setCancelable(false);
                mProgressDialog.setTitle(null);
                mProgressDialog.setMessage(getString(R.string.s_loading));
                mProgressDialog.show();
            } else {
                mProgressDialog.show();
            }
        } else  {
            mProgressDialog.dismiss();
        }

    }

}
