package com.jackrepos.ui.repodetail;

import android.support.annotation.NonNull;

import com.jackrepos.model.Repo;
import com.jackrepos.ui.common.MvpPresenter;
import com.jackrepos.ui.common.MvpView;

import java.util.List;

/**
 * Created by Navneet on 14-07-2017.
 */

public interface RepoDetailContractor {

    interface RepoDetailPresenter<View> extends MvpPresenter<View> {

        void loadRepoDetails(int requestType,String url);



    }

    interface RepoDetailView extends MvpView {

        void showRepoContributors(int count);
        void showRepoCommits(int count);
        void showRepoBranches(int count);
        void showError(String errorMsg);



    }

}
