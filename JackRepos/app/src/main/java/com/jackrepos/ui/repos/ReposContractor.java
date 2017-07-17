package com.jackrepos.ui.repos;

import android.support.annotation.NonNull;

import com.jackrepos.model.Repo;
import com.jackrepos.ui.common.MvpView;
import com.jackrepos.ui.common.MvpPresenter;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public interface ReposContractor {

    interface ReposPresenter<View> extends MvpPresenter<View> {

        void loadRepos(boolean loadMore, boolean forceUpdate);

        void openRepoDetails(@NonNull Repo repoClicked);

    }

        /*
         Interface for update UI of RepoFragment
        */

    interface ReposView extends MvpView {

        void showRepos(List<Repo> repos);

        void showReposLoading(boolean loading);

        void showRepoDetailUi(Repo repo);
        void showError(String errorMsg);

    }

}
